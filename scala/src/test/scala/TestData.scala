object TestData {
  val formId: String = "formId"
  val typeformBaseUrl: String = "http://localhost:5001"
  val typeformAccessToken: String = "accessToken"
  val typeformWorkspace: String = "workspaceId"
  val requestBody: String = s"""{
                       |            "type": "quiz",
                       |            "title": "Superheros",
                       |            "workspace": {
                       |                "href": "https://api.typeform.com/workspaces/$typeformWorkspace"
                       |            },
                       |            "settings": {
                       |                "language": "en",
                       |                "progress_bar": "proportion",
                       |                "meta": {
                       |                    "allow_indexing": false
                       |                },
                       |                "hide_navigation": false,
                       |                "is_public": true,
                       |                "is_trial": false,
                       |                "show_progress_bar": false,
                       |                "show_typeform_branding": true,
                       |                "are_uploads_public": false,
                       |                "show_time_to_complete": true,
                       |                "show_number_of_submissions": false,
                       |                "show_cookie_consent": false,
                       |                "show_question_number": false,
                       |                "show_key_hint_on_choices": true,
                       |                "autosave_progress": false,
                       |                "free_form_navigation": false,
                       |                "use_lead_qualification": false,
                       |                "pro_subdomain_enabled": false,
                       |                "capabilities": {
                       |                    "e2e_encryption": {
                       |                        "enabled": false,
                       |                        "modifiable": false
                       |                    }
                       |                }
                       |            },
                       |            "thankyou_screens": [
                       |                {
                       |                    "ref": "58922574-874d-4e40-8ff6-b60c4ab99d4e",
                       |                    "title": "",
                       |                    "type": "thankyou_screen",
                       |                    "properties": {
                       |                        "show_button": false,
                       |                        "share_icons": false,
                       |                        "button_mode": "default_redirect",
                       |                        "button_text": "again"
                       |                    }
                       |                },
                       |                {
                       |                    "ref": "default_tys",
                       |                    "title": "All done! Thanks for your time.",
                       |                    "type": "thankyou_screen",
                       |                    "properties": {
                       |                        "show_button": false,
                       |                        "share_icons": false
                       |                    }
                       |                }
                       |            ],
                       |            "fields": [
                       |                {
                       |                    "title": "Who is your favourite superhero?",
                       |                    "ref": "question-1",
                       |                    "properties": {
                       |                        "randomize": true,
                       |                        "allow_multiple_selection": false,
                       |                        "allow_other_choice": false,
                       |                        "vertical_alignment": false,
                       |                        "choices": [
                       |                            {
                       |                                "ref": "question-1-option-1",
                       |                                "label": "Superman"
                       |                            },
                       |                            {
                       |                                "ref": "question-1-option-2",
                       |                                "label": "Wonder Woman"
                       |                            },
                       |                            {
                       |                                "ref": "question-1-option-3",
                       |                                "label": "Black Panther"
                       |                            },
                       |                            {
                       |                                "ref": "question-1-option-4",
                       |                                "label": "Supergirl"
                       |                            }
                       |                        ]
                       |                    },
                       |                    "validations": {
                       |                        "required": true
                       |                    },
                       |                    "type": "multiple_choice"
                       |                }
                       |            ]
                       |        }""".stripMargin
}
