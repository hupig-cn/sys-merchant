package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.MerchantApp;
import com.weisen.www.code.yjf.merchant.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.merchant.domain.Shoplocation;
import com.weisen.www.code.yjf.merchant.repository.ShoplocationRepository;
import com.weisen.www.code.yjf.merchant.service.ShoplocationService;
import com.weisen.www.code.yjf.merchant.service.dto.ShoplocationDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.ShoplocationMapper;
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
 * Integration tests for the {@Link ShoplocationResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, MerchantApp.class})
public class ShoplocationResourceIT {

    private static final String DEFAULT_MERCHANTID = "AAAAAAAAAA";
    private static final String UPDATED_MERCHANTID = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEDATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDATE = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIERDATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIERDATE = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIERNUM = 1L;
    private static final Long UPDATED_MODIFIERNUM = 2L;

    private static final Boolean DEFAULT_LOGICDELETE = false;
    private static final Boolean UPDATED_LOGICDELETE = true;

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    @Autowired
    private ShoplocationRepository shoplocationRepository;

    @Autowired
    private ShoplocationMapper shoplocationMapper;

    @Autowired
    private ShoplocationService shoplocationService;

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

    private MockMvc restShoplocationMockMvc;

    private Shoplocation shoplocation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShoplocationResource shoplocationResource = new ShoplocationResource(shoplocationService);
        this.restShoplocationMockMvc = MockMvcBuilders.standaloneSetup(shoplocationResource)
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
    public static Shoplocation createEntity(EntityManager em) {
        Shoplocation shoplocation = new Shoplocation()
            .merchantid(DEFAULT_MERCHANTID)
            .location(DEFAULT_LOCATION)
            .num(DEFAULT_NUM)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return shoplocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shoplocation createUpdatedEntity(EntityManager em) {
        Shoplocation shoplocation = new Shoplocation()
            .merchantid(UPDATED_MERCHANTID)
            .location(UPDATED_LOCATION)
            .num(UPDATED_NUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return shoplocation;
    }

    @BeforeEach
    public void initTest() {
        shoplocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createShoplocation() throws Exception {
        int databaseSizeBeforeCreate = shoplocationRepository.findAll().size();

        // Create the Shoplocation
        ShoplocationDTO shoplocationDTO = shoplocationMapper.toDto(shoplocation);
        restShoplocationMockMvc.perform(post("/api/shoplocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoplocationDTO)))
            .andExpect(status().isCreated());

        // Validate the Shoplocation in the database
        List<Shoplocation> shoplocationList = shoplocationRepository.findAll();
        assertThat(shoplocationList).hasSize(databaseSizeBeforeCreate + 1);
        Shoplocation testShoplocation = shoplocationList.get(shoplocationList.size() - 1);
        assertThat(testShoplocation.getMerchantid()).isEqualTo(DEFAULT_MERCHANTID);
        assertThat(testShoplocation.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testShoplocation.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testShoplocation.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testShoplocation.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testShoplocation.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testShoplocation.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testShoplocation.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testShoplocation.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testShoplocation.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createShoplocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shoplocationRepository.findAll().size();

        // Create the Shoplocation with an existing ID
        shoplocation.setId(1L);
        ShoplocationDTO shoplocationDTO = shoplocationMapper.toDto(shoplocation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShoplocationMockMvc.perform(post("/api/shoplocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoplocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Shoplocation in the database
        List<Shoplocation> shoplocationList = shoplocationRepository.findAll();
        assertThat(shoplocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShoplocations() throws Exception {
        // Initialize the database
        shoplocationRepository.saveAndFlush(shoplocation);

        // Get all the shoplocationList
        restShoplocationMockMvc.perform(get("/api/shoplocations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shoplocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].merchantid").value(hasItem(DEFAULT_MERCHANTID.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierdate").value(hasItem(DEFAULT_MODIFIERDATE.toString())))
            .andExpect(jsonPath("$.[*].modifiernum").value(hasItem(DEFAULT_MODIFIERNUM.intValue())))
            .andExpect(jsonPath("$.[*].logicdelete").value(hasItem(DEFAULT_LOGICDELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())));
    }
    
    @Test
    @Transactional
    public void getShoplocation() throws Exception {
        // Initialize the database
        shoplocationRepository.saveAndFlush(shoplocation);

        // Get the shoplocation
        restShoplocationMockMvc.perform(get("/api/shoplocations/{id}", shoplocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shoplocation.getId().intValue()))
            .andExpect(jsonPath("$.merchantid").value(DEFAULT_MERCHANTID.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierdate").value(DEFAULT_MODIFIERDATE.toString()))
            .andExpect(jsonPath("$.modifiernum").value(DEFAULT_MODIFIERNUM.intValue()))
            .andExpect(jsonPath("$.logicdelete").value(DEFAULT_LOGICDELETE.booleanValue()))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingShoplocation() throws Exception {
        // Get the shoplocation
        restShoplocationMockMvc.perform(get("/api/shoplocations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShoplocation() throws Exception {
        // Initialize the database
        shoplocationRepository.saveAndFlush(shoplocation);

        int databaseSizeBeforeUpdate = shoplocationRepository.findAll().size();

        // Update the shoplocation
        Shoplocation updatedShoplocation = shoplocationRepository.findById(shoplocation.getId()).get();
        // Disconnect from session so that the updates on updatedShoplocation are not directly saved in db
        em.detach(updatedShoplocation);
        updatedShoplocation
            .merchantid(UPDATED_MERCHANTID)
            .location(UPDATED_LOCATION)
            .num(UPDATED_NUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        ShoplocationDTO shoplocationDTO = shoplocationMapper.toDto(updatedShoplocation);

        restShoplocationMockMvc.perform(put("/api/shoplocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoplocationDTO)))
            .andExpect(status().isOk());

        // Validate the Shoplocation in the database
        List<Shoplocation> shoplocationList = shoplocationRepository.findAll();
        assertThat(shoplocationList).hasSize(databaseSizeBeforeUpdate);
        Shoplocation testShoplocation = shoplocationList.get(shoplocationList.size() - 1);
        assertThat(testShoplocation.getMerchantid()).isEqualTo(UPDATED_MERCHANTID);
        assertThat(testShoplocation.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testShoplocation.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testShoplocation.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testShoplocation.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testShoplocation.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testShoplocation.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testShoplocation.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testShoplocation.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testShoplocation.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingShoplocation() throws Exception {
        int databaseSizeBeforeUpdate = shoplocationRepository.findAll().size();

        // Create the Shoplocation
        ShoplocationDTO shoplocationDTO = shoplocationMapper.toDto(shoplocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShoplocationMockMvc.perform(put("/api/shoplocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoplocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Shoplocation in the database
        List<Shoplocation> shoplocationList = shoplocationRepository.findAll();
        assertThat(shoplocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShoplocation() throws Exception {
        // Initialize the database
        shoplocationRepository.saveAndFlush(shoplocation);

        int databaseSizeBeforeDelete = shoplocationRepository.findAll().size();

        // Delete the shoplocation
        restShoplocationMockMvc.perform(delete("/api/shoplocations/{id}", shoplocation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Shoplocation> shoplocationList = shoplocationRepository.findAll();
        assertThat(shoplocationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Shoplocation.class);
        Shoplocation shoplocation1 = new Shoplocation();
        shoplocation1.setId(1L);
        Shoplocation shoplocation2 = new Shoplocation();
        shoplocation2.setId(shoplocation1.getId());
        assertThat(shoplocation1).isEqualTo(shoplocation2);
        shoplocation2.setId(2L);
        assertThat(shoplocation1).isNotEqualTo(shoplocation2);
        shoplocation1.setId(null);
        assertThat(shoplocation1).isNotEqualTo(shoplocation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShoplocationDTO.class);
        ShoplocationDTO shoplocationDTO1 = new ShoplocationDTO();
        shoplocationDTO1.setId(1L);
        ShoplocationDTO shoplocationDTO2 = new ShoplocationDTO();
        assertThat(shoplocationDTO1).isNotEqualTo(shoplocationDTO2);
        shoplocationDTO2.setId(shoplocationDTO1.getId());
        assertThat(shoplocationDTO1).isEqualTo(shoplocationDTO2);
        shoplocationDTO2.setId(2L);
        assertThat(shoplocationDTO1).isNotEqualTo(shoplocationDTO2);
        shoplocationDTO1.setId(null);
        assertThat(shoplocationDTO1).isNotEqualTo(shoplocationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(shoplocationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(shoplocationMapper.fromId(null)).isNull();
    }
}
