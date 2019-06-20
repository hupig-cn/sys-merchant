package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.MerchantApp;
import com.weisen.www.code.yjf.merchant.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.MerchantService;
import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.MerchantMapper;
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
 * Integration tests for the {@Link MerchantResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, MerchantApp.class})
public class MerchantResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_MERCHANTPHOTO = "AAAAAAAAAA";
    private static final String UPDATED_MERCHANTPHOTO = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSID = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSID = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONCESSION = 1;
    private static final Integer UPDATED_CONCESSION = 2;

    private static final Integer DEFAULT_REBATE = 1;
    private static final Integer UPDATED_REBATE = 2;

    private static final String DEFAULT_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_WEIGHT = "BBBBBBBBBB";

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
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private MerchantService merchantService;

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

    private MockMvc restMerchantMockMvc;

    private Merchant merchant;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MerchantResource merchantResource = new MerchantResource(merchantService);
        this.restMerchantMockMvc = MockMvcBuilders.standaloneSetup(merchantResource)
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
    public static Merchant createEntity(EntityManager em) {
        Merchant merchant = new Merchant()
            .userid(DEFAULT_USERID)
            .merchantphoto(DEFAULT_MERCHANTPHOTO)
            .name(DEFAULT_NAME)
            .businessid(DEFAULT_BUSINESSID)
            .state(DEFAULT_STATE)
            .address(DEFAULT_ADDRESS)
            .province(DEFAULT_PROVINCE)
            .city(DEFAULT_CITY)
            .county(DEFAULT_COUNTY)
            .concession(DEFAULT_CONCESSION)
            .rebate(DEFAULT_REBATE)
            .weight(DEFAULT_WEIGHT)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return merchant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Merchant createUpdatedEntity(EntityManager em) {
        Merchant merchant = new Merchant()
            .userid(UPDATED_USERID)
            .merchantphoto(UPDATED_MERCHANTPHOTO)
            .name(UPDATED_NAME)
            .businessid(UPDATED_BUSINESSID)
            .state(UPDATED_STATE)
            .address(UPDATED_ADDRESS)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .county(UPDATED_COUNTY)
            .concession(UPDATED_CONCESSION)
            .rebate(UPDATED_REBATE)
            .weight(UPDATED_WEIGHT)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return merchant;
    }

    @BeforeEach
    public void initTest() {
        merchant = createEntity(em);
    }

    @Test
    @Transactional
    public void createMerchant() throws Exception {
        int databaseSizeBeforeCreate = merchantRepository.findAll().size();

        // Create the Merchant
        MerchantDTO merchantDTO = merchantMapper.toDto(merchant);
        restMerchantMockMvc.perform(post("/api/merchants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merchantDTO)))
            .andExpect(status().isCreated());

        // Validate the Merchant in the database
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeCreate + 1);
        Merchant testMerchant = merchantList.get(merchantList.size() - 1);
        assertThat(testMerchant.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testMerchant.getMerchantphoto()).isEqualTo(DEFAULT_MERCHANTPHOTO);
        assertThat(testMerchant.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMerchant.getBusinessid()).isEqualTo(DEFAULT_BUSINESSID);
        assertThat(testMerchant.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testMerchant.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testMerchant.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testMerchant.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testMerchant.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testMerchant.getConcession()).isEqualTo(DEFAULT_CONCESSION);
        assertThat(testMerchant.getRebate()).isEqualTo(DEFAULT_REBATE);
        assertThat(testMerchant.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMerchant.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testMerchant.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testMerchant.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testMerchant.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testMerchant.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testMerchant.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testMerchant.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createMerchantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = merchantRepository.findAll().size();

        // Create the Merchant with an existing ID
        merchant.setId(1L);
        MerchantDTO merchantDTO = merchantMapper.toDto(merchant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMerchantMockMvc.perform(post("/api/merchants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merchantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Merchant in the database
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMerchants() throws Exception {
        // Initialize the database
        merchantRepository.saveAndFlush(merchant);

        // Get all the merchantList
        restMerchantMockMvc.perform(get("/api/merchants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(merchant.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].merchantphoto").value(hasItem(DEFAULT_MERCHANTPHOTO.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].businessid").value(hasItem(DEFAULT_BUSINESSID.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY.toString())))
            .andExpect(jsonPath("$.[*].concession").value(hasItem(DEFAULT_CONCESSION)))
            .andExpect(jsonPath("$.[*].rebate").value(hasItem(DEFAULT_REBATE)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.toString())))
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
    public void getMerchant() throws Exception {
        // Initialize the database
        merchantRepository.saveAndFlush(merchant);

        // Get the merchant
        restMerchantMockMvc.perform(get("/api/merchants/{id}", merchant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(merchant.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.merchantphoto").value(DEFAULT_MERCHANTPHOTO.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.businessid").value(DEFAULT_BUSINESSID.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY.toString()))
            .andExpect(jsonPath("$.concession").value(DEFAULT_CONCESSION))
            .andExpect(jsonPath("$.rebate").value(DEFAULT_REBATE))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.toString()))
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
    public void getNonExistingMerchant() throws Exception {
        // Get the merchant
        restMerchantMockMvc.perform(get("/api/merchants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMerchant() throws Exception {
        // Initialize the database
        merchantRepository.saveAndFlush(merchant);

        int databaseSizeBeforeUpdate = merchantRepository.findAll().size();

        // Update the merchant
        Merchant updatedMerchant = merchantRepository.findById(merchant.getId()).get();
        // Disconnect from session so that the updates on updatedMerchant are not directly saved in db
        em.detach(updatedMerchant);
        updatedMerchant
            .userid(UPDATED_USERID)
            .merchantphoto(UPDATED_MERCHANTPHOTO)
            .name(UPDATED_NAME)
            .businessid(UPDATED_BUSINESSID)
            .state(UPDATED_STATE)
            .address(UPDATED_ADDRESS)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .county(UPDATED_COUNTY)
            .concession(UPDATED_CONCESSION)
            .rebate(UPDATED_REBATE)
            .weight(UPDATED_WEIGHT)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        MerchantDTO merchantDTO = merchantMapper.toDto(updatedMerchant);

        restMerchantMockMvc.perform(put("/api/merchants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merchantDTO)))
            .andExpect(status().isOk());

        // Validate the Merchant in the database
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeUpdate);
        Merchant testMerchant = merchantList.get(merchantList.size() - 1);
        assertThat(testMerchant.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testMerchant.getMerchantphoto()).isEqualTo(UPDATED_MERCHANTPHOTO);
        assertThat(testMerchant.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMerchant.getBusinessid()).isEqualTo(UPDATED_BUSINESSID);
        assertThat(testMerchant.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testMerchant.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testMerchant.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testMerchant.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testMerchant.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testMerchant.getConcession()).isEqualTo(UPDATED_CONCESSION);
        assertThat(testMerchant.getRebate()).isEqualTo(UPDATED_REBATE);
        assertThat(testMerchant.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMerchant.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testMerchant.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testMerchant.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testMerchant.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testMerchant.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testMerchant.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testMerchant.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingMerchant() throws Exception {
        int databaseSizeBeforeUpdate = merchantRepository.findAll().size();

        // Create the Merchant
        MerchantDTO merchantDTO = merchantMapper.toDto(merchant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMerchantMockMvc.perform(put("/api/merchants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merchantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Merchant in the database
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMerchant() throws Exception {
        // Initialize the database
        merchantRepository.saveAndFlush(merchant);

        int databaseSizeBeforeDelete = merchantRepository.findAll().size();

        // Delete the merchant
        restMerchantMockMvc.perform(delete("/api/merchants/{id}", merchant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Merchant> merchantList = merchantRepository.findAll();
        assertThat(merchantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Merchant.class);
        Merchant merchant1 = new Merchant();
        merchant1.setId(1L);
        Merchant merchant2 = new Merchant();
        merchant2.setId(merchant1.getId());
        assertThat(merchant1).isEqualTo(merchant2);
        merchant2.setId(2L);
        assertThat(merchant1).isNotEqualTo(merchant2);
        merchant1.setId(null);
        assertThat(merchant1).isNotEqualTo(merchant2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MerchantDTO.class);
        MerchantDTO merchantDTO1 = new MerchantDTO();
        merchantDTO1.setId(1L);
        MerchantDTO merchantDTO2 = new MerchantDTO();
        assertThat(merchantDTO1).isNotEqualTo(merchantDTO2);
        merchantDTO2.setId(merchantDTO1.getId());
        assertThat(merchantDTO1).isEqualTo(merchantDTO2);
        merchantDTO2.setId(2L);
        assertThat(merchantDTO1).isNotEqualTo(merchantDTO2);
        merchantDTO1.setId(null);
        assertThat(merchantDTO1).isNotEqualTo(merchantDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(merchantMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(merchantMapper.fromId(null)).isNull();
    }
}
