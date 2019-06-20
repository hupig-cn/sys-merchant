package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.MerchantApp;
import com.weisen.www.code.yjf.merchant.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.merchant.domain.Dishesorder;
import com.weisen.www.code.yjf.merchant.repository.DishesorderRepository;
import com.weisen.www.code.yjf.merchant.service.DishesorderService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishesorderMapper;
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
 * Integration tests for the {@Link DishesorderResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, MerchantApp.class})
public class DishesorderResourceIT {

    private static final String DEFAULT_MERCHANTID = "AAAAAAAAAA";
    private static final String UPDATED_MERCHANTID = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

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
    private DishesorderRepository dishesorderRepository;

    @Autowired
    private DishesorderMapper dishesorderMapper;

    @Autowired
    private DishesorderService dishesorderService;

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

    private MockMvc restDishesorderMockMvc;

    private Dishesorder dishesorder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DishesorderResource dishesorderResource = new DishesorderResource(dishesorderService);
        this.restDishesorderMockMvc = MockMvcBuilders.standaloneSetup(dishesorderResource)
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
    public static Dishesorder createEntity(EntityManager em) {
        Dishesorder dishesorder = new Dishesorder()
            .merchantid(DEFAULT_MERCHANTID)
            .location(DEFAULT_LOCATION)
            .name(DEFAULT_NAME)
            .state(DEFAULT_STATE)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return dishesorder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dishesorder createUpdatedEntity(EntityManager em) {
        Dishesorder dishesorder = new Dishesorder()
            .merchantid(UPDATED_MERCHANTID)
            .location(UPDATED_LOCATION)
            .name(UPDATED_NAME)
            .state(UPDATED_STATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return dishesorder;
    }

    @BeforeEach
    public void initTest() {
        dishesorder = createEntity(em);
    }

    @Test
    @Transactional
    public void createDishesorder() throws Exception {
        int databaseSizeBeforeCreate = dishesorderRepository.findAll().size();

        // Create the Dishesorder
        DishesorderDTO dishesorderDTO = dishesorderMapper.toDto(dishesorder);
        restDishesorderMockMvc.perform(post("/api/dishesorders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesorderDTO)))
            .andExpect(status().isCreated());

        // Validate the Dishesorder in the database
        List<Dishesorder> dishesorderList = dishesorderRepository.findAll();
        assertThat(dishesorderList).hasSize(databaseSizeBeforeCreate + 1);
        Dishesorder testDishesorder = dishesorderList.get(dishesorderList.size() - 1);
        assertThat(testDishesorder.getMerchantid()).isEqualTo(DEFAULT_MERCHANTID);
        assertThat(testDishesorder.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testDishesorder.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDishesorder.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testDishesorder.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testDishesorder.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testDishesorder.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testDishesorder.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testDishesorder.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testDishesorder.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testDishesorder.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createDishesorderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dishesorderRepository.findAll().size();

        // Create the Dishesorder with an existing ID
        dishesorder.setId(1L);
        DishesorderDTO dishesorderDTO = dishesorderMapper.toDto(dishesorder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDishesorderMockMvc.perform(post("/api/dishesorders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesorderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dishesorder in the database
        List<Dishesorder> dishesorderList = dishesorderRepository.findAll();
        assertThat(dishesorderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDishesorders() throws Exception {
        // Initialize the database
        dishesorderRepository.saveAndFlush(dishesorder);

        // Get all the dishesorderList
        restDishesorderMockMvc.perform(get("/api/dishesorders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dishesorder.getId().intValue())))
            .andExpect(jsonPath("$.[*].merchantid").value(hasItem(DEFAULT_MERCHANTID.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
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
    public void getDishesorder() throws Exception {
        // Initialize the database
        dishesorderRepository.saveAndFlush(dishesorder);

        // Get the dishesorder
        restDishesorderMockMvc.perform(get("/api/dishesorders/{id}", dishesorder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dishesorder.getId().intValue()))
            .andExpect(jsonPath("$.merchantid").value(DEFAULT_MERCHANTID.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
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
    public void getNonExistingDishesorder() throws Exception {
        // Get the dishesorder
        restDishesorderMockMvc.perform(get("/api/dishesorders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDishesorder() throws Exception {
        // Initialize the database
        dishesorderRepository.saveAndFlush(dishesorder);

        int databaseSizeBeforeUpdate = dishesorderRepository.findAll().size();

        // Update the dishesorder
        Dishesorder updatedDishesorder = dishesorderRepository.findById(dishesorder.getId()).get();
        // Disconnect from session so that the updates on updatedDishesorder are not directly saved in db
        em.detach(updatedDishesorder);
        updatedDishesorder
            .merchantid(UPDATED_MERCHANTID)
            .location(UPDATED_LOCATION)
            .name(UPDATED_NAME)
            .state(UPDATED_STATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        DishesorderDTO dishesorderDTO = dishesorderMapper.toDto(updatedDishesorder);

        restDishesorderMockMvc.perform(put("/api/dishesorders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesorderDTO)))
            .andExpect(status().isOk());

        // Validate the Dishesorder in the database
        List<Dishesorder> dishesorderList = dishesorderRepository.findAll();
        assertThat(dishesorderList).hasSize(databaseSizeBeforeUpdate);
        Dishesorder testDishesorder = dishesorderList.get(dishesorderList.size() - 1);
        assertThat(testDishesorder.getMerchantid()).isEqualTo(UPDATED_MERCHANTID);
        assertThat(testDishesorder.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testDishesorder.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDishesorder.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testDishesorder.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testDishesorder.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testDishesorder.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testDishesorder.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testDishesorder.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testDishesorder.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testDishesorder.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingDishesorder() throws Exception {
        int databaseSizeBeforeUpdate = dishesorderRepository.findAll().size();

        // Create the Dishesorder
        DishesorderDTO dishesorderDTO = dishesorderMapper.toDto(dishesorder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDishesorderMockMvc.perform(put("/api/dishesorders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesorderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dishesorder in the database
        List<Dishesorder> dishesorderList = dishesorderRepository.findAll();
        assertThat(dishesorderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDishesorder() throws Exception {
        // Initialize the database
        dishesorderRepository.saveAndFlush(dishesorder);

        int databaseSizeBeforeDelete = dishesorderRepository.findAll().size();

        // Delete the dishesorder
        restDishesorderMockMvc.perform(delete("/api/dishesorders/{id}", dishesorder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Dishesorder> dishesorderList = dishesorderRepository.findAll();
        assertThat(dishesorderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dishesorder.class);
        Dishesorder dishesorder1 = new Dishesorder();
        dishesorder1.setId(1L);
        Dishesorder dishesorder2 = new Dishesorder();
        dishesorder2.setId(dishesorder1.getId());
        assertThat(dishesorder1).isEqualTo(dishesorder2);
        dishesorder2.setId(2L);
        assertThat(dishesorder1).isNotEqualTo(dishesorder2);
        dishesorder1.setId(null);
        assertThat(dishesorder1).isNotEqualTo(dishesorder2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DishesorderDTO.class);
        DishesorderDTO dishesorderDTO1 = new DishesorderDTO();
        dishesorderDTO1.setId(1L);
        DishesorderDTO dishesorderDTO2 = new DishesorderDTO();
        assertThat(dishesorderDTO1).isNotEqualTo(dishesorderDTO2);
        dishesorderDTO2.setId(dishesorderDTO1.getId());
        assertThat(dishesorderDTO1).isEqualTo(dishesorderDTO2);
        dishesorderDTO2.setId(2L);
        assertThat(dishesorderDTO1).isNotEqualTo(dishesorderDTO2);
        dishesorderDTO1.setId(null);
        assertThat(dishesorderDTO1).isNotEqualTo(dishesorderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dishesorderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dishesorderMapper.fromId(null)).isNull();
    }
}
