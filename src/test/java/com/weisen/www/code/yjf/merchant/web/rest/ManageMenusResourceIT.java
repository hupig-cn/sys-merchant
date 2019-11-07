package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.MerchantApp;
import com.weisen.www.code.yjf.merchant.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.merchant.domain.ManageMenus;
import com.weisen.www.code.yjf.merchant.repository.ManageMenusRepository;
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
 * Integration tests for the {@Link ManageMenusResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, MerchantApp.class})
public class ManageMenusResourceIT {

    private static final Long DEFAULT_PID = 1L;
    private static final Long UPDATED_PID = 2L;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_HREF = "AAAAAAAAAA";
    private static final String UPDATED_HREF = "BBBBBBBBBB";

    private static final Long DEFAULT_STATE = 1L;
    private static final Long UPDATED_STATE = 2L;

    private static final Long DEFAULT_CSORT = 1L;
    private static final Long UPDATED_CSORT = 2L;

    private static final String DEFAULT_NOTICE = "AAAAAAAAAA";
    private static final String UPDATED_NOTICE = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET = "AAAAAAAAAA";
    private static final String UPDATED_TARGET = "BBBBBBBBBB";

    @Autowired
    private ManageMenusRepository manageMenusRepository;

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

    private MockMvc restManageMenusMockMvc;

    private ManageMenus manageMenus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ManageMenusResource manageMenusResource = new ManageMenusResource(manageMenusRepository);
        this.restManageMenusMockMvc = MockMvcBuilders.standaloneSetup(manageMenusResource)
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
    public static ManageMenus createEntity(EntityManager em) {
        ManageMenus manageMenus = new ManageMenus()
            .pid(DEFAULT_PID)
            .title(DEFAULT_TITLE)
            .icon(DEFAULT_ICON)
            .href(DEFAULT_HREF)
            .state(DEFAULT_STATE)
            .csort(DEFAULT_CSORT)
            .notice(DEFAULT_NOTICE)
            .target(DEFAULT_TARGET);
        return manageMenus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManageMenus createUpdatedEntity(EntityManager em) {
        ManageMenus manageMenus = new ManageMenus()
            .pid(UPDATED_PID)
            .title(UPDATED_TITLE)
            .icon(UPDATED_ICON)
            .href(UPDATED_HREF)
            .state(UPDATED_STATE)
            .csort(UPDATED_CSORT)
            .notice(UPDATED_NOTICE)
            .target(UPDATED_TARGET);
        return manageMenus;
    }

    @BeforeEach
    public void initTest() {
        manageMenus = createEntity(em);
    }

    @Test
    @Transactional
    public void createManageMenus() throws Exception {
        int databaseSizeBeforeCreate = manageMenusRepository.findAll().size();

        // Create the ManageMenus
        restManageMenusMockMvc.perform(post("/api/manage-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manageMenus)))
            .andExpect(status().isCreated());

        // Validate the ManageMenus in the database
        List<ManageMenus> manageMenusList = manageMenusRepository.findAll();
        assertThat(manageMenusList).hasSize(databaseSizeBeforeCreate + 1);
        ManageMenus testManageMenus = manageMenusList.get(manageMenusList.size() - 1);
        assertThat(testManageMenus.getPid()).isEqualTo(DEFAULT_PID);
        assertThat(testManageMenus.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testManageMenus.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testManageMenus.getHref()).isEqualTo(DEFAULT_HREF);
        assertThat(testManageMenus.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testManageMenus.getCsort()).isEqualTo(DEFAULT_CSORT);
        assertThat(testManageMenus.getNotice()).isEqualTo(DEFAULT_NOTICE);
        assertThat(testManageMenus.getTarget()).isEqualTo(DEFAULT_TARGET);
    }

    @Test
    @Transactional
    public void createManageMenusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manageMenusRepository.findAll().size();

        // Create the ManageMenus with an existing ID
        manageMenus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManageMenusMockMvc.perform(post("/api/manage-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manageMenus)))
            .andExpect(status().isBadRequest());

        // Validate the ManageMenus in the database
        List<ManageMenus> manageMenusList = manageMenusRepository.findAll();
        assertThat(manageMenusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllManageMenus() throws Exception {
        // Initialize the database
        manageMenusRepository.saveAndFlush(manageMenus);

        // Get all the manageMenusList
        restManageMenusMockMvc.perform(get("/api/manage-menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manageMenus.getId().intValue())))
            .andExpect(jsonPath("$.[*].pid").value(hasItem(DEFAULT_PID.intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())))
            .andExpect(jsonPath("$.[*].href").value(hasItem(DEFAULT_HREF.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.intValue())))
            .andExpect(jsonPath("$.[*].csort").value(hasItem(DEFAULT_CSORT.intValue())))
            .andExpect(jsonPath("$.[*].notice").value(hasItem(DEFAULT_NOTICE.toString())))
            .andExpect(jsonPath("$.[*].target").value(hasItem(DEFAULT_TARGET.toString())));
    }
    
    @Test
    @Transactional
    public void getManageMenus() throws Exception {
        // Initialize the database
        manageMenusRepository.saveAndFlush(manageMenus);

        // Get the manageMenus
        restManageMenusMockMvc.perform(get("/api/manage-menus/{id}", manageMenus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manageMenus.getId().intValue()))
            .andExpect(jsonPath("$.pid").value(DEFAULT_PID.intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON.toString()))
            .andExpect(jsonPath("$.href").value(DEFAULT_HREF.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.intValue()))
            .andExpect(jsonPath("$.csort").value(DEFAULT_CSORT.intValue()))
            .andExpect(jsonPath("$.notice").value(DEFAULT_NOTICE.toString()))
            .andExpect(jsonPath("$.target").value(DEFAULT_TARGET.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingManageMenus() throws Exception {
        // Get the manageMenus
        restManageMenusMockMvc.perform(get("/api/manage-menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManageMenus() throws Exception {
        // Initialize the database
        manageMenusRepository.saveAndFlush(manageMenus);

        int databaseSizeBeforeUpdate = manageMenusRepository.findAll().size();

        // Update the manageMenus
        ManageMenus updatedManageMenus = manageMenusRepository.findById(manageMenus.getId()).get();
        // Disconnect from session so that the updates on updatedManageMenus are not directly saved in db
        em.detach(updatedManageMenus);
        updatedManageMenus
            .pid(UPDATED_PID)
            .title(UPDATED_TITLE)
            .icon(UPDATED_ICON)
            .href(UPDATED_HREF)
            .state(UPDATED_STATE)
            .csort(UPDATED_CSORT)
            .notice(UPDATED_NOTICE)
            .target(UPDATED_TARGET);

        restManageMenusMockMvc.perform(put("/api/manage-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedManageMenus)))
            .andExpect(status().isOk());

        // Validate the ManageMenus in the database
        List<ManageMenus> manageMenusList = manageMenusRepository.findAll();
        assertThat(manageMenusList).hasSize(databaseSizeBeforeUpdate);
        ManageMenus testManageMenus = manageMenusList.get(manageMenusList.size() - 1);
        assertThat(testManageMenus.getPid()).isEqualTo(UPDATED_PID);
        assertThat(testManageMenus.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testManageMenus.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testManageMenus.getHref()).isEqualTo(UPDATED_HREF);
        assertThat(testManageMenus.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testManageMenus.getCsort()).isEqualTo(UPDATED_CSORT);
        assertThat(testManageMenus.getNotice()).isEqualTo(UPDATED_NOTICE);
        assertThat(testManageMenus.getTarget()).isEqualTo(UPDATED_TARGET);
    }

    @Test
    @Transactional
    public void updateNonExistingManageMenus() throws Exception {
        int databaseSizeBeforeUpdate = manageMenusRepository.findAll().size();

        // Create the ManageMenus

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManageMenusMockMvc.perform(put("/api/manage-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manageMenus)))
            .andExpect(status().isBadRequest());

        // Validate the ManageMenus in the database
        List<ManageMenus> manageMenusList = manageMenusRepository.findAll();
        assertThat(manageMenusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManageMenus() throws Exception {
        // Initialize the database
        manageMenusRepository.saveAndFlush(manageMenus);

        int databaseSizeBeforeDelete = manageMenusRepository.findAll().size();

        // Delete the manageMenus
        restManageMenusMockMvc.perform(delete("/api/manage-menus/{id}", manageMenus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ManageMenus> manageMenusList = manageMenusRepository.findAll();
        assertThat(manageMenusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManageMenus.class);
        ManageMenus manageMenus1 = new ManageMenus();
        manageMenus1.setId(1L);
        ManageMenus manageMenus2 = new ManageMenus();
        manageMenus2.setId(manageMenus1.getId());
        assertThat(manageMenus1).isEqualTo(manageMenus2);
        manageMenus2.setId(2L);
        assertThat(manageMenus1).isNotEqualTo(manageMenus2);
        manageMenus1.setId(null);
        assertThat(manageMenus1).isNotEqualTo(manageMenus2);
    }
}
