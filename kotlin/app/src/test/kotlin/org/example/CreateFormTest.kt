package org.example

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class CreateFormTest {
    private val server = MockWebServer()

    @BeforeEach
    fun beforeEach() {
        server.start()
    }

    @Test
    fun `typeform creates a form successfully`() {
        val typeformAccessToken = "4rj9if40uf49843"
        val baseUrl: HttpUrl = server.url("")
        val typeformBaseUrl = "${baseUrl.scheme}://${baseUrl.host}:${baseUrl.port}"
        val formId = "formId"
        server.enqueue(
            MockResponse()
                .setHeader("Location", "$typeformBaseUrl/forms/$formId")
                .setResponseCode(201)
        )

        val createForm = CreateForm(
            typeformBaseUrl = typeformBaseUrl,
            typeformAccessToken = typeformAccessToken,
            typeformWorkspace = workspace,
            okHttpClient = OkHttpClient()
        )

        val result = createForm.execute()

        result.fold(
            { Assertions.fail(it.toString()) },
            { assertThat(it).isEqualTo(formId) }
        )

        val request: RecordedRequest = server.takeRequest()
        assertThat(request.method).isEqualTo("POST")
        assertThat(request.requestUrl.toString()).isEqualTo("$typeformBaseUrl/forms")
        assertThat(request.getHeader("Content-Type")).isEqualTo("application/json; charset=utf-8")
        assertThat(request.getHeader("Accept")).isEqualTo("application/json")
        assertThat(request.getHeader("Authorization")).isEqualTo("Bearer $typeformAccessToken")
        assertThat(request.body.readUtf8()).isEqualTo(requestBody)
    }

    @AfterEach
    fun afterEach() {
        server.shutdown()
    }

    private val workspace = "asdfsdaf"

    private val requestBody = """
        {
            "type": "quiz",
            "title": "Superheros",
            "workspace": {
                "href": "https://api.typeform.com/workspaces/${workspace}"
            },
            "settings": {
                "language": "en",
                "progress_bar": "proportion",
                "meta": {
                    "allow_indexing": false
                },
                "hide_navigation": false,
                "is_public": true,
                "is_trial": false,
                "show_progress_bar": false,
                "show_typeform_branding": true,
                "are_uploads_public": false,
                "show_time_to_complete": true,
                "show_number_of_submissions": false,
                "show_cookie_consent": false,
                "show_question_number": false,
                "show_key_hint_on_choices": true,
                "autosave_progress": false,
                "free_form_navigation": false,
                "use_lead_qualification": false,
                "pro_subdomain_enabled": false,
                "capabilities": {
                    "e2e_encryption": {
                        "enabled": false,
                        "modifiable": false
                    }
                }
            },
            "thankyou_screens": [
                {
                    "ref": "58922574-874d-4e40-8ff6-b60c4ab99d4e",
                    "title": "",
                    "type": "thankyou_screen",
                    "properties": {
                        "show_button": false,
                        "share_icons": false,
                        "button_mode": "default_redirect",
                        "button_text": "again"
                    }
                },
                {
                    "ref": "default_tys",
                    "title": "All done! Thanks for your time.",
                    "type": "thankyou_screen",
                    "properties": {
                        "show_button": false,
                        "share_icons": false
                    }
                }
            ],
            "fields": [
                {
                    "title": "Who is your favourite superhero?",
                    "ref": "question-1",
                    "properties": {
                        "randomize": true,
                        "allow_multiple_selection": false,
                        "allow_other_choice": false,
                        "vertical_alignment": false,
                        "choices": [
                            {
                                "ref": "question-1-option-1",
                                "label": "Superman"
                            },
                            {
                                "ref": "question-1-option-2",
                                "label": "Wonder Woman"
                            },
                            {
                                "ref": "question-1-option-3",
                                "label": "Black Panther"
                            },
                            {
                                "ref": "question-1-option-4",
                                "label": "Supergirl"
                            }
                        ]
                    },
                    "validations": {
                        "required": true
                    },
                    "type": "multiple_choice"
                }
            ]
        }
    """.trimIndent()

}