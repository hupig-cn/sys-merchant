package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.MerchantApp;
import com.weisen.www.code.yjf.merchant.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.merchant.domain.Dishes;
import com.weisen.www.code.yjf.merchant.repository.DishesRepository;
import com.weisen.www.code.yjf.merchant.service.DishesService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishesMapper;
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
 * Integration tests for the {@Link DishesResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, MerchantApp.class})
public class DishesResourceIT {

    private static final String DEFAULT_MERCHANTID = "AAAAAAAAAA";
    private static final String UPDATED_MERCHANTID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_DISHESTYPEID = "AAAAAAAAAA";
    private static final String UPDATED_DISHESTYPEID = "BBBBBBBBBB";

    private static final String DEFAULT_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_PRICE = "BBBBBBBBBB";

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
    private DishesRepository dishesRepository;

    @Autowired
    private DishesMapper dishesMapper;

    @Autowired
    private DishesService dishesService;

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

    private MockMvc restDishesMockMvc;

    private Dishes dishes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DishesResource dishesResource = new DishesResource(dishesService);
        this.restDishesMockMvc = MockMvcBuilders.standaloneSetup(dishesResource)
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
    public static Dishes createEntity(EntityManager em) {
        Dishes dishes = new Dishes()
            .merchantid(DEFAULT_MERCHANTID)
            .name(DEFAULT_NAME)
            .image(DEFAULT_IMAGE)
            .dishestypeid(DEFAULT_DISHESTYPEID)
            .price(DEFAULT_PRICE)
            .num(DEFAULT_NUM)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return dishes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dishes createUpdatedEntity(EntityManager em) {
        Dishes dishes = new Dishes()
            .merchantid(UPDATED_MERCHANTID)
            .name(UPDATED_NAME)
            .image(UPDATED_IMAGE)
            .dishestypeid(UPDATED_DISHESTYPEID)
            .price(UPDATED_PRICE)
            .num(UPDATED_NUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return dishes;
    }

    @BeforeEach
    public void initTest() {
        dishes = createEntity(em);
    }

    @Test
    @Transactional
    public void createDishes() throws Exception {
        int databaseSizeBeforeCreate = dishesRepository.findAll().size();

        // Create the Dishes
        DishesDTO dishesDTO = dishesMapper.toDto(dishes);
        restDishesMockMvc.perform(post("/api/dishes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesDTO)))
            .andExpect(status().isCreated());

        // Validate the Dishes in the database
        List<Dishes> dishesList = dishesRepository.findAll();
        assertThat(dishesList).hasSize(databaseSizeBeforeCreate + 1);
        Dishes testDishes = dishesList.get(dishesList.size() - 1);
        assertThat(testDishes.getMerchantid()).isEqualTo(DEFAULT_MERCHANTID);
        assertThat(testDishes.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDishes.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testDishes.getDishestypeid()).isEqualTo(DEFAULT_DISHESTYPEID);
        assertThat(testDishes.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testDishes.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testDishes.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testDishes.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testDishes.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testDishes.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testDishes.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testDishes.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testDishes.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createDishesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dishesRepository.findAll().size();

        // Create the Dishes with an existing ID
        dishes.setId(1L);
        DishesDTO dishesDTO = dishesMapper.toDto(dishes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDishesMockMvc.perform(post("/api/dishes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dishes in the database
        List<Dishes> dishesList = dishesRepository.findAll();
        assertThat(dishesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDishes() throws Exception {
        // Initialize the database
        dishesRepository.saveAndFlush(dishes);

        // Get all the dishesList
        restDishesMockMvc.perform(get("/api/dishes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dishes.getId().intValue())))
            .andExpect(jsonPath("$.[*].merchantid").value(hasItem(DEFAULT_MERCHANTID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].dishestypeid").value(hasItem(DEFAULT_DISHESTYPEID.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.toString())))
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
    public void getDishes() throws Exception {
        // Initialize the database
        dishesRepository.saveAndFlush(dishes);

        // Get the dishes
        restDishesMockMvc.perform(get("/api/dishes/{id}", dishes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dishes.getId().intValue()))
            .andExpect(jsonPath("$.merchantid").value(DEFAULT_MERCHANTID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.dishestypeid").value(DEFAULT_DISHESTYPEID.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.toString()))
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
    public void getNonExistingDishes() throws Exception {
        // Get the dishes
        restDishesMockMvc.perform(get("/api/dishes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDishes() throws Exception {
        // Initialize the database
        dishesRepository.saveAndFlush(dishes);

        int databaseSizeBeforeUpdate = dishesRepository.findAll().size();

        // Update the dishes
        Dishes updatedDishes = dishesRepository.findById(dishes.getId()).get();
        // Disconnect from session so that the updates on updatedDishes are not directly saved in db
        em.detach(updatedDishes);
        updatedDishes
            .merchantid(UPDATED_MERCHANTID)
            .name(UPDATED_NAME)
            .image(UPDATED_IMAGE)
            .dishestypeid(UPDATED_DISHESTYPEID)
            .price(UPDATED_PRICE)
            .num(UPDATED_NUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        DishesDTO dishesDTO = dishesMapper.toDto(updatedDishes);

        restDishesMockMvc.perform(put("/api/dishes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesDTO)))
            .andExpect(status().isOk());

        // Validate the Dishes in the database
        List<Dishes> dishesList = dishesRepository.findAll();
        assertThat(dishesList).hasSize(databaseSizeBeforeUpdate);
        Dishes testDishes = dishesList.get(dishesList.size() - 1);
        assertThat(testDishes.getMerchantid()).isEqualTo(UPDATED_MERCHANTID);
        assertThat(testDishes.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDishes.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testDishes.getDishestypeid()).isEqualTo(UPDATED_DISHESTYPEID);
        assertThat(testDishes.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testDishes.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testDishes.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testDishes.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testDishes.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testDishes.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testDishes.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testDishes.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testDishes.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingDishes() throws Exception {
        int databaseSizeBeforeUpdate = dishesRepository.findAll().size();

        // Create the Dishes
        DishesDTO dishesDTO = dishesMapper.toDto(dishes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDishesMockMvc.perform(put("/api/dishes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dishes in the database
        List<Dishes> dishesList = dishesRepository.findAll();
        assertThat(dishesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDishes() throws Exception {
        // Initialize the database
        dishesRepository.saveAndFlush(dishes);

        int databaseSizeBeforeDelete = dishesRepository.findAll().size();

        // Delete the dishes
        restDishesMockMvc.perform(delete("/api/dishes/{id}", dishes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Dishes> dishesList = dishesRepository.findAll();
        assertThat(dishesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dishes.class);
        Dishes dishes1 = new Dishes();
        dishes1.setId(1L);
        Dishes dishes2 = new Dishes();
        dishes2.setId(dishes1.getId());
        assertThat(dishes1).isEqualTo(dishes2);
        dishes2.setId(2L);
        assertThat(dishes1).isNotEqualTo(dishes2);
        dishes1.setId(null);
        assertThat(dishes1).isNotEqualTo(dishes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DishesDTO.class);
        DishesDTO dishesDTO1 = new DishesDTO();
        dishesDTO1.setId(1L);
        DishesDTO dishesDTO2 = new DishesDTO();
        assertThat(dishesDTO1).isNotEqualTo(dishesDTO2);
        dishesDTO2.setId(dishesDTO1.getId());
        assertThat(dishesDTO1).isEqualTo(dishesDTO2);
        dishesDTO2.setId(2L);
        assertThat(dishesDTO1).isNotEqualTo(dishesDTO2);
        dishesDTO1.setId(null);
        assertThat(dishesDTO1).isNotEqualTo(dishesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dishesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dishesMapper.fromId(null)).isNull();
    }
}
