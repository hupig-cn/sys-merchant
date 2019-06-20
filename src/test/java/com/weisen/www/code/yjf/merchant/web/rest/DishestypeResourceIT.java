package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.MerchantApp;
import com.weisen.www.code.yjf.merchant.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.repository.DishestypeRepository;
import com.weisen.www.code.yjf.merchant.service.DishestypeService;
import com.weisen.www.code.yjf.merchant.service.dto.DishestypeDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishestypeMapper;
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
 * Integration tests for the {@Link DishestypeResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, MerchantApp.class})
public class DishestypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

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
    private DishestypeRepository dishestypeRepository;

    @Autowired
    private DishestypeMapper dishestypeMapper;

    @Autowired
    private DishestypeService dishestypeService;

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

    private MockMvc restDishestypeMockMvc;

    private Dishestype dishestype;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DishestypeResource dishestypeResource = new DishestypeResource(dishestypeService);
        this.restDishestypeMockMvc = MockMvcBuilders.standaloneSetup(dishestypeResource)
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
    public static Dishestype createEntity(EntityManager em) {
        Dishestype dishestype = new Dishestype()
            .name(DEFAULT_NAME)
            .state(DEFAULT_STATE)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return dishestype;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dishestype createUpdatedEntity(EntityManager em) {
        Dishestype dishestype = new Dishestype()
            .name(UPDATED_NAME)
            .state(UPDATED_STATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return dishestype;
    }

    @BeforeEach
    public void initTest() {
        dishestype = createEntity(em);
    }

    @Test
    @Transactional
    public void createDishestype() throws Exception {
        int databaseSizeBeforeCreate = dishestypeRepository.findAll().size();

        // Create the Dishestype
        DishestypeDTO dishestypeDTO = dishestypeMapper.toDto(dishestype);
        restDishestypeMockMvc.perform(post("/api/dishestypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishestypeDTO)))
            .andExpect(status().isCreated());

        // Validate the Dishestype in the database
        List<Dishestype> dishestypeList = dishestypeRepository.findAll();
        assertThat(dishestypeList).hasSize(databaseSizeBeforeCreate + 1);
        Dishestype testDishestype = dishestypeList.get(dishestypeList.size() - 1);
        assertThat(testDishestype.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDishestype.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testDishestype.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testDishestype.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testDishestype.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testDishestype.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testDishestype.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testDishestype.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testDishestype.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createDishestypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dishestypeRepository.findAll().size();

        // Create the Dishestype with an existing ID
        dishestype.setId(1L);
        DishestypeDTO dishestypeDTO = dishestypeMapper.toDto(dishestype);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDishestypeMockMvc.perform(post("/api/dishestypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishestypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dishestype in the database
        List<Dishestype> dishestypeList = dishestypeRepository.findAll();
        assertThat(dishestypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDishestypes() throws Exception {
        // Initialize the database
        dishestypeRepository.saveAndFlush(dishestype);

        // Get all the dishestypeList
        restDishestypeMockMvc.perform(get("/api/dishestypes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dishestype.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
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
    public void getDishestype() throws Exception {
        // Initialize the database
        dishestypeRepository.saveAndFlush(dishestype);

        // Get the dishestype
        restDishestypeMockMvc.perform(get("/api/dishestypes/{id}", dishestype.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dishestype.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
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
    public void getNonExistingDishestype() throws Exception {
        // Get the dishestype
        restDishestypeMockMvc.perform(get("/api/dishestypes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDishestype() throws Exception {
        // Initialize the database
        dishestypeRepository.saveAndFlush(dishestype);

        int databaseSizeBeforeUpdate = dishestypeRepository.findAll().size();

        // Update the dishestype
        Dishestype updatedDishestype = dishestypeRepository.findById(dishestype.getId()).get();
        // Disconnect from session so that the updates on updatedDishestype are not directly saved in db
        em.detach(updatedDishestype);
        updatedDishestype
            .name(UPDATED_NAME)
            .state(UPDATED_STATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        DishestypeDTO dishestypeDTO = dishestypeMapper.toDto(updatedDishestype);

        restDishestypeMockMvc.perform(put("/api/dishestypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishestypeDTO)))
            .andExpect(status().isOk());

        // Validate the Dishestype in the database
        List<Dishestype> dishestypeList = dishestypeRepository.findAll();
        assertThat(dishestypeList).hasSize(databaseSizeBeforeUpdate);
        Dishestype testDishestype = dishestypeList.get(dishestypeList.size() - 1);
        assertThat(testDishestype.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDishestype.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testDishestype.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testDishestype.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testDishestype.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testDishestype.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testDishestype.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testDishestype.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testDishestype.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingDishestype() throws Exception {
        int databaseSizeBeforeUpdate = dishestypeRepository.findAll().size();

        // Create the Dishestype
        DishestypeDTO dishestypeDTO = dishestypeMapper.toDto(dishestype);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDishestypeMockMvc.perform(put("/api/dishestypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishestypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dishestype in the database
        List<Dishestype> dishestypeList = dishestypeRepository.findAll();
        assertThat(dishestypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDishestype() throws Exception {
        // Initialize the database
        dishestypeRepository.saveAndFlush(dishestype);

        int databaseSizeBeforeDelete = dishestypeRepository.findAll().size();

        // Delete the dishestype
        restDishestypeMockMvc.perform(delete("/api/dishestypes/{id}", dishestype.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Dishestype> dishestypeList = dishestypeRepository.findAll();
        assertThat(dishestypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dishestype.class);
        Dishestype dishestype1 = new Dishestype();
        dishestype1.setId(1L);
        Dishestype dishestype2 = new Dishestype();
        dishestype2.setId(dishestype1.getId());
        assertThat(dishestype1).isEqualTo(dishestype2);
        dishestype2.setId(2L);
        assertThat(dishestype1).isNotEqualTo(dishestype2);
        dishestype1.setId(null);
        assertThat(dishestype1).isNotEqualTo(dishestype2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DishestypeDTO.class);
        DishestypeDTO dishestypeDTO1 = new DishestypeDTO();
        dishestypeDTO1.setId(1L);
        DishestypeDTO dishestypeDTO2 = new DishestypeDTO();
        assertThat(dishestypeDTO1).isNotEqualTo(dishestypeDTO2);
        dishestypeDTO2.setId(dishestypeDTO1.getId());
        assertThat(dishestypeDTO1).isEqualTo(dishestypeDTO2);
        dishestypeDTO2.setId(2L);
        assertThat(dishestypeDTO1).isNotEqualTo(dishestypeDTO2);
        dishestypeDTO1.setId(null);
        assertThat(dishestypeDTO1).isNotEqualTo(dishestypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dishestypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dishestypeMapper.fromId(null)).isNull();
    }
}
