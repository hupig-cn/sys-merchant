package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.MerchantApp;
import com.weisen.www.code.yjf.merchant.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.merchant.domain.Dishesbuy;
import com.weisen.www.code.yjf.merchant.repository.DishesbuyRepository;
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
 * Integration tests for the {@Link DishesbuyResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, MerchantApp.class})
public class DishesbuyResourceIT {

    private static final Long DEFAULT_MERCHANTID = 1L;
    private static final Long UPDATED_MERCHANTID = 2L;

    private static final Long DEFAULT_USERID = 1L;
    private static final Long UPDATED_USERID = 2L;

    private static final Long DEFAULT_DISHESID = 1L;
    private static final Long UPDATED_DISHESID = 2L;

    private static final Integer DEFAULT_COUNT = 1;
    private static final Integer UPDATED_COUNT = 2;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Integer DEFAULT_STATE = 1;
    private static final Integer UPDATED_STATE = 2;

    private static final Long DEFAULT_CREATOR = 1L;
    private static final Long UPDATED_CREATOR = 2L;

    private static final String DEFAULT_CREATETIME = "AAAAAAAAAA";
    private static final String UPDATED_CREATETIME = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIER = 1L;
    private static final Long UPDATED_MODIFIER = 2L;

    private static final Integer DEFAULT_MODIFINUM = 1;
    private static final Integer UPDATED_MODIFINUM = 2;

    private static final String DEFAULT_MODIFITIME = "AAAAAAAAAA";
    private static final String UPDATED_MODIFITIME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOGIC_DELETE = 1;
    private static final Integer UPDATED_LOGIC_DELETE = 2;

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDERID = 1L;
    private static final Long UPDATED_ORDERID = 2L;

    @Autowired
    private DishesbuyRepository dishesbuyRepository;

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

    private MockMvc restDishesbuyMockMvc;

    private Dishesbuy dishesbuy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DishesbuyResource dishesbuyResource = new DishesbuyResource(dishesbuyRepository);
        this.restDishesbuyMockMvc = MockMvcBuilders.standaloneSetup(dishesbuyResource)
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
    public static Dishesbuy createEntity(EntityManager em) {
        Dishesbuy dishesbuy = new Dishesbuy()
            .merchantid(DEFAULT_MERCHANTID)
            .userid(DEFAULT_USERID)
            .dishesid(DEFAULT_DISHESID)
            .count(DEFAULT_COUNT)
            .price(DEFAULT_PRICE)
            .state(DEFAULT_STATE)
            .creator(DEFAULT_CREATOR)
            .createtime(DEFAULT_CREATETIME)
            .modifier(DEFAULT_MODIFIER)
            .modifinum(DEFAULT_MODIFINUM)
            .modifitime(DEFAULT_MODIFITIME)
            .logicDelete(DEFAULT_LOGIC_DELETE)
            .other(DEFAULT_OTHER)
            .orderid(DEFAULT_ORDERID);
        return dishesbuy;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dishesbuy createUpdatedEntity(EntityManager em) {
        Dishesbuy dishesbuy = new Dishesbuy()
            .merchantid(UPDATED_MERCHANTID)
            .userid(UPDATED_USERID)
            .dishesid(UPDATED_DISHESID)
            .count(UPDATED_COUNT)
            .price(UPDATED_PRICE)
            .state(UPDATED_STATE)
            .creator(UPDATED_CREATOR)
            .createtime(UPDATED_CREATETIME)
            .modifier(UPDATED_MODIFIER)
            .modifinum(UPDATED_MODIFINUM)
            .modifitime(UPDATED_MODIFITIME)
            .logicDelete(UPDATED_LOGIC_DELETE)
            .other(UPDATED_OTHER)
            .orderid(UPDATED_ORDERID);
        return dishesbuy;
    }

    @BeforeEach
    public void initTest() {
        dishesbuy = createEntity(em);
    }

    @Test
    @Transactional
    public void createDishesbuy() throws Exception {
        int databaseSizeBeforeCreate = dishesbuyRepository.findAll().size();

        // Create the Dishesbuy
        restDishesbuyMockMvc.perform(post("/api/dishesbuys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesbuy)))
            .andExpect(status().isCreated());

        // Validate the Dishesbuy in the database
        List<Dishesbuy> dishesbuyList = dishesbuyRepository.findAll();
        assertThat(dishesbuyList).hasSize(databaseSizeBeforeCreate + 1);
        Dishesbuy testDishesbuy = dishesbuyList.get(dishesbuyList.size() - 1);
        assertThat(testDishesbuy.getMerchantid()).isEqualTo(DEFAULT_MERCHANTID);
        assertThat(testDishesbuy.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testDishesbuy.getDishesid()).isEqualTo(DEFAULT_DISHESID);
        assertThat(testDishesbuy.getCount()).isEqualTo(DEFAULT_COUNT);
        assertThat(testDishesbuy.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testDishesbuy.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testDishesbuy.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testDishesbuy.getCreatetime()).isEqualTo(DEFAULT_CREATETIME);
        assertThat(testDishesbuy.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testDishesbuy.getModifinum()).isEqualTo(DEFAULT_MODIFINUM);
        assertThat(testDishesbuy.getModifitime()).isEqualTo(DEFAULT_MODIFITIME);
        assertThat(testDishesbuy.getLogicDelete()).isEqualTo(DEFAULT_LOGIC_DELETE);
        assertThat(testDishesbuy.getOther()).isEqualTo(DEFAULT_OTHER);
        assertThat(testDishesbuy.getOrderid()).isEqualTo(DEFAULT_ORDERID);
    }

    @Test
    @Transactional
    public void createDishesbuyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dishesbuyRepository.findAll().size();

        // Create the Dishesbuy with an existing ID
        dishesbuy.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDishesbuyMockMvc.perform(post("/api/dishesbuys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesbuy)))
            .andExpect(status().isBadRequest());

        // Validate the Dishesbuy in the database
        List<Dishesbuy> dishesbuyList = dishesbuyRepository.findAll();
        assertThat(dishesbuyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDishesbuys() throws Exception {
        // Initialize the database
        dishesbuyRepository.saveAndFlush(dishesbuy);

        // Get all the dishesbuyList
        restDishesbuyMockMvc.perform(get("/api/dishesbuys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dishesbuy.getId().intValue())))
            .andExpect(jsonPath("$.[*].merchantid").value(hasItem(DEFAULT_MERCHANTID.intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.intValue())))
            .andExpect(jsonPath("$.[*].dishesid").value(hasItem(DEFAULT_DISHESID.intValue())))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.intValue())))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.intValue())))
            .andExpect(jsonPath("$.[*].modifinum").value(hasItem(DEFAULT_MODIFINUM)))
            .andExpect(jsonPath("$.[*].modifitime").value(hasItem(DEFAULT_MODIFITIME.toString())))
            .andExpect(jsonPath("$.[*].logicDelete").value(hasItem(DEFAULT_LOGIC_DELETE)))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())))
            .andExpect(jsonPath("$.[*].orderid").value(hasItem(DEFAULT_ORDERID.intValue())));
    }
    
    @Test
    @Transactional
    public void getDishesbuy() throws Exception {
        // Initialize the database
        dishesbuyRepository.saveAndFlush(dishesbuy);

        // Get the dishesbuy
        restDishesbuyMockMvc.perform(get("/api/dishesbuys/{id}", dishesbuy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dishesbuy.getId().intValue()))
            .andExpect(jsonPath("$.merchantid").value(DEFAULT_MERCHANTID.intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.intValue()))
            .andExpect(jsonPath("$.dishesid").value(DEFAULT_DISHESID.intValue()))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.intValue()))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.intValue()))
            .andExpect(jsonPath("$.modifinum").value(DEFAULT_MODIFINUM))
            .andExpect(jsonPath("$.modifitime").value(DEFAULT_MODIFITIME.toString()))
            .andExpect(jsonPath("$.logicDelete").value(DEFAULT_LOGIC_DELETE))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()))
            .andExpect(jsonPath("$.orderid").value(DEFAULT_ORDERID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDishesbuy() throws Exception {
        // Get the dishesbuy
        restDishesbuyMockMvc.perform(get("/api/dishesbuys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDishesbuy() throws Exception {
        // Initialize the database
        dishesbuyRepository.saveAndFlush(dishesbuy);

        int databaseSizeBeforeUpdate = dishesbuyRepository.findAll().size();

        // Update the dishesbuy
        Dishesbuy updatedDishesbuy = dishesbuyRepository.findById(dishesbuy.getId()).get();
        // Disconnect from session so that the updates on updatedDishesbuy are not directly saved in db
        em.detach(updatedDishesbuy);
        updatedDishesbuy
            .merchantid(UPDATED_MERCHANTID)
            .userid(UPDATED_USERID)
            .dishesid(UPDATED_DISHESID)
            .count(UPDATED_COUNT)
            .price(UPDATED_PRICE)
            .state(UPDATED_STATE)
            .creator(UPDATED_CREATOR)
            .createtime(UPDATED_CREATETIME)
            .modifier(UPDATED_MODIFIER)
            .modifinum(UPDATED_MODIFINUM)
            .modifitime(UPDATED_MODIFITIME)
            .logicDelete(UPDATED_LOGIC_DELETE)
            .other(UPDATED_OTHER)
            .orderid(UPDATED_ORDERID);

        restDishesbuyMockMvc.perform(put("/api/dishesbuys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDishesbuy)))
            .andExpect(status().isOk());

        // Validate the Dishesbuy in the database
        List<Dishesbuy> dishesbuyList = dishesbuyRepository.findAll();
        assertThat(dishesbuyList).hasSize(databaseSizeBeforeUpdate);
        Dishesbuy testDishesbuy = dishesbuyList.get(dishesbuyList.size() - 1);
        assertThat(testDishesbuy.getMerchantid()).isEqualTo(UPDATED_MERCHANTID);
        assertThat(testDishesbuy.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testDishesbuy.getDishesid()).isEqualTo(UPDATED_DISHESID);
        assertThat(testDishesbuy.getCount()).isEqualTo(UPDATED_COUNT);
        assertThat(testDishesbuy.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testDishesbuy.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testDishesbuy.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testDishesbuy.getCreatetime()).isEqualTo(UPDATED_CREATETIME);
        assertThat(testDishesbuy.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testDishesbuy.getModifinum()).isEqualTo(UPDATED_MODIFINUM);
        assertThat(testDishesbuy.getModifitime()).isEqualTo(UPDATED_MODIFITIME);
        assertThat(testDishesbuy.getLogicDelete()).isEqualTo(UPDATED_LOGIC_DELETE);
        assertThat(testDishesbuy.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testDishesbuy.getOrderid()).isEqualTo(UPDATED_ORDERID);
    }

    @Test
    @Transactional
    public void updateNonExistingDishesbuy() throws Exception {
        int databaseSizeBeforeUpdate = dishesbuyRepository.findAll().size();

        // Create the Dishesbuy

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDishesbuyMockMvc.perform(put("/api/dishesbuys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dishesbuy)))
            .andExpect(status().isBadRequest());

        // Validate the Dishesbuy in the database
        List<Dishesbuy> dishesbuyList = dishesbuyRepository.findAll();
        assertThat(dishesbuyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDishesbuy() throws Exception {
        // Initialize the database
        dishesbuyRepository.saveAndFlush(dishesbuy);

        int databaseSizeBeforeDelete = dishesbuyRepository.findAll().size();

        // Delete the dishesbuy
        restDishesbuyMockMvc.perform(delete("/api/dishesbuys/{id}", dishesbuy.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Dishesbuy> dishesbuyList = dishesbuyRepository.findAll();
        assertThat(dishesbuyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dishesbuy.class);
        Dishesbuy dishesbuy1 = new Dishesbuy();
        dishesbuy1.setId(1L);
        Dishesbuy dishesbuy2 = new Dishesbuy();
        dishesbuy2.setId(dishesbuy1.getId());
        assertThat(dishesbuy1).isEqualTo(dishesbuy2);
        dishesbuy2.setId(2L);
        assertThat(dishesbuy1).isNotEqualTo(dishesbuy2);
        dishesbuy1.setId(null);
        assertThat(dishesbuy1).isNotEqualTo(dishesbuy2);
    }
}
