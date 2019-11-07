package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.MerchantApp;
import com.weisen.www.code.yjf.merchant.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.merchant.domain.ManageUser;
import com.weisen.www.code.yjf.merchant.repository.ManageUserRepository;
import com.weisen.www.code.yjf.merchant.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.weisen.www.code.yjf.merchant.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ManageUserResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, MerchantApp.class})
public class ManageUserResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_RELNAME = "AAAAAAAAAA";
    private static final String UPDATED_RELNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_STATE = 1L;
    private static final Long UPDATED_STATE = 2L;

    private static final String DEFAULT_CTIME = "AAAAAAAAAA";
    private static final String UPDATED_CTIME = "BBBBBBBBBB";

    @Autowired
    private ManageUserRepository manageUserRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restManageUserMockMvc;

    private ManageUser manageUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ManageUserResource manageUserResource = new ManageUserResource(manageUserRepository);
        this.restManageUserMockMvc = MockMvcBuilders.standaloneSetup(manageUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManageUser createEntity(EntityManager em) {
        ManageUser manageUser = new ManageUser()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .relname(DEFAULT_RELNAME)
            .state(DEFAULT_STATE)
            .ctime(DEFAULT_CTIME);
        return manageUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManageUser createUpdatedEntity(EntityManager em) {
        ManageUser manageUser = new ManageUser()
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .relname(UPDATED_RELNAME)
            .state(UPDATED_STATE)
            .ctime(UPDATED_CTIME);
        return manageUser;
    }

    @BeforeEach
    public void initTest() {
        manageUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createManageUser() throws Exception {
        int databaseSizeBeforeCreate = manageUserRepository.findAll().size();

        // Create the ManageUser
        restManageUserMockMvc.perform(post("/api/manage-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manageUser)))
            .andExpect(status().isCreated());

        // Validate the ManageUser in the database
        List<ManageUser> manageUserList = manageUserRepository.findAll();
        assertThat(manageUserList).hasSize(databaseSizeBeforeCreate + 1);
        ManageUser testManageUser = manageUserList.get(manageUserList.size() - 1);
        assertThat(testManageUser.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testManageUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testManageUser.getRelname()).isEqualTo(DEFAULT_RELNAME);
        assertThat(testManageUser.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testManageUser.getCtime()).isEqualTo(DEFAULT_CTIME);
    }

    @Test
    @Transactional
    public void createManageUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manageUserRepository.findAll().size();

        // Create the ManageUser with an existing ID
        manageUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManageUserMockMvc.perform(post("/api/manage-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manageUser)))
            .andExpect(status().isBadRequest());

        // Validate the ManageUser in the database
        List<ManageUser> manageUserList = manageUserRepository.findAll();
        assertThat(manageUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllManageUsers() throws Exception {
        // Initialize the database
        manageUserRepository.saveAndFlush(manageUser);

        // Get all the manageUserList
        restManageUserMockMvc.perform(get("/api/manage-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manageUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].relname").value(hasItem(DEFAULT_RELNAME.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.intValue())))
            .andExpect(jsonPath("$.[*].ctime").value(hasItem(DEFAULT_CTIME.toString())));
    }
    
    @Test
    @Transactional
    public void getManageUser() throws Exception {
        // Initialize the database
        manageUserRepository.saveAndFlush(manageUser);

        // Get the manageUser
        restManageUserMockMvc.perform(get("/api/manage-users/{id}", manageUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manageUser.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.relname").value(DEFAULT_RELNAME.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.intValue()))
            .andExpect(jsonPath("$.ctime").value(DEFAULT_CTIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingManageUser() throws Exception {
        // Get the manageUser
        restManageUserMockMvc.perform(get("/api/manage-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManageUser() throws Exception {
        // Initialize the database
        manageUserRepository.saveAndFlush(manageUser);

        int databaseSizeBeforeUpdate = manageUserRepository.findAll().size();

        // Update the manageUser
        ManageUser updatedManageUser = manageUserRepository.findById(manageUser.getId()).get();
        // Disconnect from session so that the updates on updatedManageUser are not directly saved in db
        em.detach(updatedManageUser);
        updatedManageUser
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .relname(UPDATED_RELNAME)
            .state(UPDATED_STATE)
            .ctime(UPDATED_CTIME);

        restManageUserMockMvc.perform(put("/api/manage-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedManageUser)))
            .andExpect(status().isOk());

        // Validate the ManageUser in the database
        List<ManageUser> manageUserList = manageUserRepository.findAll();
        assertThat(manageUserList).hasSize(databaseSizeBeforeUpdate);
        ManageUser testManageUser = manageUserList.get(manageUserList.size() - 1);
        assertThat(testManageUser.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testManageUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testManageUser.getRelname()).isEqualTo(UPDATED_RELNAME);
        assertThat(testManageUser.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testManageUser.getCtime()).isEqualTo(UPDATED_CTIME);
    }

    @Test
    @Transactional
    public void updateNonExistingManageUser() throws Exception {
        int databaseSizeBeforeUpdate = manageUserRepository.findAll().size();

        // Create the ManageUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManageUserMockMvc.perform(put("/api/manage-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manageUser)))
            .andExpect(status().isBadRequest());

        // Validate the ManageUser in the database
        List<ManageUser> manageUserList = manageUserRepository.findAll();
        assertThat(manageUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManageUser() throws Exception {
        // Initialize the database
        manageUserRepository.saveAndFlush(manageUser);

        int databaseSizeBeforeDelete = manageUserRepository.findAll().size();

        // Delete the manageUser
        restManageUserMockMvc.perform(delete("/api/manage-users/{id}", manageUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ManageUser> manageUserList = manageUserRepository.findAll();
        assertThat(manageUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManageUser.class);
        ManageUser manageUser1 = new ManageUser();
        manageUser1.setId(1L);
        ManageUser manageUser2 = new ManageUser();
        manageUser2.setId(manageUser1.getId());
        assertThat(manageUser1).isEqualTo(manageUser2);
        manageUser2.setId(2L);
        assertThat(manageUser1).isNotEqualTo(manageUser2);
        manageUser1.setId(null);
        assertThat(manageUser1).isNotEqualTo(manageUser2);
    }
}
