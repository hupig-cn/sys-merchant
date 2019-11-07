package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.MerchantApp;
import com.weisen.www.code.yjf.merchant.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.merchant.domain.ManageOrg;
import com.weisen.www.code.yjf.merchant.repository.ManageOrgRepository;
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
 * Integration tests for the {@Link ManageOrgResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, MerchantApp.class})
public class ManageOrgResourceIT {

    private static final Long DEFAULT_PID = 1L;
    private static final Long UPDATED_PID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_STATE = 1L;
    private static final Long UPDATED_STATE = 2L;

    private static final Long DEFAULT_CSORT = 1L;
    private static final Long UPDATED_CSORT = 2L;

    private static final String DEFAULT_CTIME = "AAAAAAAAAA";
    private static final String UPDATED_CTIME = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private ManageOrgRepository manageOrgRepository;

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

    private MockMvc restManageOrgMockMvc;

    private ManageOrg manageOrg;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ManageOrgResource manageOrgResource = new ManageOrgResource(manageOrgRepository);
        this.restManageOrgMockMvc = MockMvcBuilders.standaloneSetup(manageOrgResource)
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
    public static ManageOrg createEntity(EntityManager em) {
        ManageOrg manageOrg = new ManageOrg()
            .pid(DEFAULT_PID)
            .name(DEFAULT_NAME)
            .state(DEFAULT_STATE)
            .csort(DEFAULT_CSORT)
            .ctime(DEFAULT_CTIME)
            .desc(DEFAULT_DESC);
        return manageOrg;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManageOrg createUpdatedEntity(EntityManager em) {
        ManageOrg manageOrg = new ManageOrg()
            .pid(UPDATED_PID)
            .name(UPDATED_NAME)
            .state(UPDATED_STATE)
            .csort(UPDATED_CSORT)
            .ctime(UPDATED_CTIME)
            .desc(UPDATED_DESC);
        return manageOrg;
    }

    @BeforeEach
    public void initTest() {
        manageOrg = createEntity(em);
    }

    @Test
    @Transactional
    public void createManageOrg() throws Exception {
        int databaseSizeBeforeCreate = manageOrgRepository.findAll().size();

        // Create the ManageOrg
        restManageOrgMockMvc.perform(post("/api/manage-orgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manageOrg)))
            .andExpect(status().isCreated());

        // Validate the ManageOrg in the database
        List<ManageOrg> manageOrgList = manageOrgRepository.findAll();
        assertThat(manageOrgList).hasSize(databaseSizeBeforeCreate + 1);
        ManageOrg testManageOrg = manageOrgList.get(manageOrgList.size() - 1);
        assertThat(testManageOrg.getPid()).isEqualTo(DEFAULT_PID);
        assertThat(testManageOrg.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testManageOrg.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testManageOrg.getCsort()).isEqualTo(DEFAULT_CSORT);
        assertThat(testManageOrg.getCtime()).isEqualTo(DEFAULT_CTIME);
        assertThat(testManageOrg.getDesc()).isEqualTo(DEFAULT_DESC);
    }

    @Test
    @Transactional
    public void createManageOrgWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manageOrgRepository.findAll().size();

        // Create the ManageOrg with an existing ID
        manageOrg.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManageOrgMockMvc.perform(post("/api/manage-orgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manageOrg)))
            .andExpect(status().isBadRequest());

        // Validate the ManageOrg in the database
        List<ManageOrg> manageOrgList = manageOrgRepository.findAll();
        assertThat(manageOrgList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllManageOrgs() throws Exception {
        // Initialize the database
        manageOrgRepository.saveAndFlush(manageOrg);

        // Get all the manageOrgList
        restManageOrgMockMvc.perform(get("/api/manage-orgs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manageOrg.getId().intValue())))
            .andExpect(jsonPath("$.[*].pid").value(hasItem(DEFAULT_PID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.intValue())))
            .andExpect(jsonPath("$.[*].csort").value(hasItem(DEFAULT_CSORT.intValue())))
            .andExpect(jsonPath("$.[*].ctime").value(hasItem(DEFAULT_CTIME.toString())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }
    
    @Test
    @Transactional
    public void getManageOrg() throws Exception {
        // Initialize the database
        manageOrgRepository.saveAndFlush(manageOrg);

        // Get the manageOrg
        restManageOrgMockMvc.perform(get("/api/manage-orgs/{id}", manageOrg.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manageOrg.getId().intValue()))
            .andExpect(jsonPath("$.pid").value(DEFAULT_PID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.intValue()))
            .andExpect(jsonPath("$.csort").value(DEFAULT_CSORT.intValue()))
            .andExpect(jsonPath("$.ctime").value(DEFAULT_CTIME.toString()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingManageOrg() throws Exception {
        // Get the manageOrg
        restManageOrgMockMvc.perform(get("/api/manage-orgs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManageOrg() throws Exception {
        // Initialize the database
        manageOrgRepository.saveAndFlush(manageOrg);

        int databaseSizeBeforeUpdate = manageOrgRepository.findAll().size();

        // Update the manageOrg
        ManageOrg updatedManageOrg = manageOrgRepository.findById(manageOrg.getId()).get();
        // Disconnect from session so that the updates on updatedManageOrg are not directly saved in db
        em.detach(updatedManageOrg);
        updatedManageOrg
            .pid(UPDATED_PID)
            .name(UPDATED_NAME)
            .state(UPDATED_STATE)
            .csort(UPDATED_CSORT)
            .ctime(UPDATED_CTIME)
            .desc(UPDATED_DESC);

        restManageOrgMockMvc.perform(put("/api/manage-orgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedManageOrg)))
            .andExpect(status().isOk());

        // Validate the ManageOrg in the database
        List<ManageOrg> manageOrgList = manageOrgRepository.findAll();
        assertThat(manageOrgList).hasSize(databaseSizeBeforeUpdate);
        ManageOrg testManageOrg = manageOrgList.get(manageOrgList.size() - 1);
        assertThat(testManageOrg.getPid()).isEqualTo(UPDATED_PID);
        assertThat(testManageOrg.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testManageOrg.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testManageOrg.getCsort()).isEqualTo(UPDATED_CSORT);
        assertThat(testManageOrg.getCtime()).isEqualTo(UPDATED_CTIME);
        assertThat(testManageOrg.getDesc()).isEqualTo(UPDATED_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingManageOrg() throws Exception {
        int databaseSizeBeforeUpdate = manageOrgRepository.findAll().size();

        // Create the ManageOrg

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManageOrgMockMvc.perform(put("/api/manage-orgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manageOrg)))
            .andExpect(status().isBadRequest());

        // Validate the ManageOrg in the database
        List<ManageOrg> manageOrgList = manageOrgRepository.findAll();
        assertThat(manageOrgList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManageOrg() throws Exception {
        // Initialize the database
        manageOrgRepository.saveAndFlush(manageOrg);

        int databaseSizeBeforeDelete = manageOrgRepository.findAll().size();

        // Delete the manageOrg
        restManageOrgMockMvc.perform(delete("/api/manage-orgs/{id}", manageOrg.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ManageOrg> manageOrgList = manageOrgRepository.findAll();
        assertThat(manageOrgList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManageOrg.class);
        ManageOrg manageOrg1 = new ManageOrg();
        manageOrg1.setId(1L);
        ManageOrg manageOrg2 = new ManageOrg();
        manageOrg2.setId(manageOrg1.getId());
        assertThat(manageOrg1).isEqualTo(manageOrg2);
        manageOrg2.setId(2L);
        assertThat(manageOrg1).isNotEqualTo(manageOrg2);
        manageOrg1.setId(null);
        assertThat(manageOrg1).isNotEqualTo(manageOrg2);
    }
}
