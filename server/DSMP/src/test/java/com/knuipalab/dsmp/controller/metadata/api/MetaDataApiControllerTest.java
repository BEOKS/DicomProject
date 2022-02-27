package com.knuipalab.dsmp.controller.metadata.api;

import com.knuipalab.dsmp.configuration.auth.CustomOAuth2UserService;
import com.knuipalab.dsmp.domain.metadata.MetaData;
import com.knuipalab.dsmp.dto.metadata.MetaDataCreateAllRequestDto;
import com.knuipalab.dsmp.dto.metadata.MetaDataCreateRequestDto;
import com.knuipalab.dsmp.dto.metadata.MetaDataResponseDto;
import com.knuipalab.dsmp.dto.metadata.MetaDataUpdateRequestDto;
import com.knuipalab.dsmp.service.metadata.MetaDataService;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MetaDataApiController.class)
class MetaDataApiControllerTest {

    @MockBean
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @MockBean
    private MetaDataService metaDataService;

    public String strBody = "{\n" +
            "   \"stored_dicom_id\": 145125,\n" +
            "    \"anonymized_id\": 1028011,\n" +
            "    \"age\": 53,\n" +
            "    \"modality\": \"MG\",\n" +
            "    \"manufacturer\": \"HOLOGIC, Inc.\",\n" +
            "    \"manufacturerModelName\": \"Lorad Selenia\",\n" +
            "    \"class non-pCR: 0 pCR: 1\": 0,\n" +
            "    \"left: 0 right: 1\": 1,\n" +
            "    \"ER\": 1,\n" +
            "    \"PR\": 1,\n" +
            "    \"HER2\": 1,\n" +
            "    \"non-IDC: 0\\nIDC: 1\": 1,\n" +
            "    \"compressionForce\": 173.5019\n" +
            "  }";

    public String strBodyList = "[{\n" +
            "   \"stored_dicom_id\": 145125,\n" +
            "    \"anonymized_id\": 1028011,\n" +
            "    \"age\": 53,\n" +
            "    \"modality\": \"MG\",\n" +
            "    \"manufacturer\": \"HOLOGIC, Inc.\",\n" +
            "    \"manufacturerModelName\": \"Lorad Selenia\",\n" +
            "    \"class non-pCR: 0 pCR: 1\": 0,\n" +
            "    \"left: 0 right: 1\": 1,\n" +
            "    \"ER\": 1,\n" +
            "    \"PR\": 1,\n" +
            "    \"HER2\": 1,\n" +
            "    \"non-IDC: 0\\nIDC: 1\": 1,\n" +
            "    \"compressionForce\": 173.5019\n" +
            "  },\n" +
            "  {\n" +
            "   \"stored_dicom_id\": 145126,\n" +
            "    \"anonymized_id\": 1028012,\n" +
            "    \"age\": 54,\n" +
            "    \"modality\": \"MG\",\n" +
            "    \"manufacturer\": \"HOLOGIC, Inc.\",\n" +
            "    \"manufacturerModelName\": \"Lorad Selenia\",\n" +
            "    \"class non-pCR: 0 pCR: 1\": 0,\n" +
            "    \"left: 0 right: 1\": 1,\n" +
            "    \"ER\": 1,\n" +
            "    \"PR\": 1,\n" +
            "    \"HER2\": 1,\n" +
            "    \"non-IDC: 0\\nIDC: 1\": 1,\n" +
            "    \"compressionForce\": 173.5019\n" +
            "  }]";

    public MetaData createTestData(){
        String metaId = "12345";
        String proId = "54321";
        Bson body = Document.parse(strBody);

        MetaData metaData = MetaData.builder()
                .metadataId(metaId)
                .projectId(proId)
                .body(body)
                .build();

        return metaData;
    }

    public List<MetaData> createTestDummyData(){
        List<MetaData> metaDataList = new ArrayList<>();
        String metaId = "12345";
        String proId = "54321";

        MetaDataCreateAllRequestDto metaDataCreateAllRequestDto = new MetaDataCreateAllRequestDto(proId,strBodyList);
        List<Document>bodyList = metaDataCreateAllRequestDto.getBodyList();
        String projectId = metaDataCreateAllRequestDto.getProjectId();

        for(Document body : bodyList) {
            MetaData metaData = new MetaData().builder()
                    .metadataId(metaId)
                    .projectId(projectId)
                    .body(body).build();
            metaDataList.add(metaData);
            metaId = String.valueOf((Integer.parseInt(metaId)+1));
        }

        return metaDataList;
    }

    @WithMockUser
    @DisplayName("Find by ProjectId")
    @Test
    void findByProjectidTest() throws Exception {

        //given
        List<MetaData> metaDataList = createTestDummyData();

        List<MetaDataResponseDto> metaDataResponseDtoList = metaDataList.stream()
                .map(metaData -> new MetaDataResponseDto(metaData))
                .collect(Collectors.toList());

        //when
        given(metaDataService.findByProjectId("54321"))
                .willReturn(metaDataResponseDtoList);

        mvc.perform(get("/api/MetaData/54321"))
                .andExpect(status().isOk()) // status 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //JSON 반환
                .andExpect(jsonPath("$.status",is(200)))
                .andExpect(jsonPath("$.count",is(2)))
                .andExpect(jsonPath("$.body.[0].metadataId", is("12345")))
                .andExpect(jsonPath("$.body.[0].projectId", is("54321")))
                .andExpect(jsonPath("$.body.[0].body.age", is(53))) // body 확인
                .andExpect(jsonPath("$.body.[1].metadataId", is("12346")))
                .andExpect(jsonPath("$.body.[1].projectId", is("54321")))
                .andExpect(jsonPath("$.body.[1].body.age", is(54))) // body 확인
                .andDo(print())
                ;
    }

    @WithMockUser
    @DisplayName("Insert by ProjectId")
    @Test
    void insertTest() throws Exception {

        String projectId = "54321";

        //Dto
        MetaDataCreateRequestDto metaDataCreateRequestDto = new MetaDataCreateRequestDto(projectId,strBody);

        mvc.perform(post("/api/MetaData/54321")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(strBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is(200)))
                .andDo(print())
                ;
    }

    @WithMockUser
    @DisplayName("Insert all by ProjectID")
    @Test
    void insertAllTest() throws Exception {

        String projectId = "54321";

        //Dto
        MetaDataCreateAllRequestDto metaDataCreateAllRequestDto = new MetaDataCreateAllRequestDto(projectId,strBodyList);

        mvc.perform(post("/api/MetaDataList/54321")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(strBodyList))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is(200)))
                .andDo(print())
        ;
    }

    @WithMockUser
    @DisplayName("Update By MetadataId")
    @Test
    void updateTest() throws Exception{

        String updatedStrBody = "{\n" +
                "   \"stored_dicom_id\": 283918,\n" +
                "    \"anonymized_id\": 3389322,\n" +
                "    \"age\": 77,\n" +
                "    \"modality\": \"MG\",\n" +
                "    \"manufacturer\": \"HOLOGIC, Inc.\",\n" +
                "    \"manufacturerModelName\": \"Lorad Selenia\",\n" +
                "    \"class non-pCR: 0 pCR: 1\": 0,\n" +
                "    \"left: 0 right: 1\": 1,\n" +
                "    \"ER\": 1,\n" +
                "    \"PR\": 1,\n" +
                "    \"HER2\": 1,\n" +
                "    \"non-IDC: 0\\nIDC: 1\": 1,\n" +
                "    \"compressionForce\": 173.5019\n" +
                "  }";

        //Dto
        MetaDataUpdateRequestDto metaDataUpdateRequestDto = new MetaDataUpdateRequestDto(updatedStrBody);

        mvc.perform(put("/api/MetaData/12345")
                .content(updatedStrBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is(200)))
                .andDo(print())
                ;
    }

    @WithMockUser
    @DisplayName("Delete by MetadataId")
    @Test
    void deleteByIdTest() throws Exception{
        mvc.perform(delete("/api/MetaData/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is(200)))
                .andDo(print())
        ;
    }

}

