package spring.jsample.docs;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.PathParametersSnippet;
import org.springframework.restdocs.request.RequestDocumentation;

public class AppDocs {

	private static Stream<FieldDescriptor> allFields() {
		return Stream.concat(basicFields(), Stream.of(
				PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.STRING).description("application id"),
				PayloadDocumentation.fieldWithPath("createdAt").type(JsonFieldType.VARIES)
						.description("application created date"),
				PayloadDocumentation.fieldWithPath("lastModifiedDate").type(JsonFieldType.VARIES)
						.description("application last modified date")));
	}

	private static Stream<FieldDescriptor> basicFields() {
		return Stream.of(
				PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("application name"),
				PayloadDocumentation.fieldWithPath("running").type(JsonFieldType.BOOLEAN)
						.description("application running status"));
	}

	public static ResponseFieldsSnippet getResponseFields() {
		return PayloadDocumentation.responseFields(allFields().collect(Collectors.toList()));
	}

	public static ResponseFieldsSnippet getAllResponseFields() {
		Stream<FieldDescriptor> responseFields = Stream.of(
				PayloadDocumentation.fieldWithPath("[].name").type(JsonFieldType.STRING).description("application name"),
				PayloadDocumentation.fieldWithPath("[].running").type(JsonFieldType.BOOLEAN).description("application running status"),
				PayloadDocumentation.fieldWithPath("[].id").type(JsonFieldType.STRING).description("application id"),
				PayloadDocumentation.fieldWithPath("[].createdAt").type(JsonFieldType.VARIES).description("application created date"),
				PayloadDocumentation.fieldWithPath("[].lastModifiedDate").type(JsonFieldType.VARIES)
						.description("application last modified date"));

		return PayloadDocumentation.responseFields(responseFields.collect(Collectors.toList()));
	}

	public static RequestFieldsSnippet addRequestFields() {
		Stream<FieldDescriptor> requestFields = Stream.concat(basicFields(),
				Stream.of(
						PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.STRING)
								.description("application id").optional(),
						PayloadDocumentation.fieldWithPath("createdAt").type(JsonFieldType.VARIES)
								.description("application created date").optional(),
						PayloadDocumentation.fieldWithPath("lastModifiedDate").type(JsonFieldType.VARIES)
								.description("application last modified date").optional()));

		return PayloadDocumentation.requestFields(requestFields.collect(Collectors.toList()));
	}

	public static RequestFieldsSnippet updateRequestFields() {
		Stream<FieldDescriptor> requestFields = Stream.concat(basicFields(),
				Stream.of(
						PayloadDocumentation.fieldWithPath("id").type(JsonFieldType.STRING)
								.description("application id").optional(),
						PayloadDocumentation.fieldWithPath("createdAt").type(JsonFieldType.VARIES)
								.description("application created date").optional(),
						PayloadDocumentation.fieldWithPath("lastModifiedDate").type(JsonFieldType.VARIES)
								.description("application last modified date")));

		return PayloadDocumentation.requestFields(requestFields.collect(Collectors.toList()));

	}

	public static PathParametersSnippet pathParameter() {
		return RequestDocumentation
				.pathParameters(RequestDocumentation.parameterWithName("id").description("application id"));
	}
}
