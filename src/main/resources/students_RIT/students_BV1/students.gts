{
    "editableResource": {
        "createdTimestamp": "1621433929719",
        "createdUser": "u334929",
        "resourceConfig": {
            "loggingConfiguration": {
                "debug": "false",
                "writeStubEventsToDB": "true"
            },
            "scenario": {
                "continueOnFail": "true",
                "id": "662edd41:17984ab6a50:-596e",
                "type": "scenario",
                "version": "1.0",
                "loggingConfiguration": {
                    "debug": "false",
                    "writeStubEventsToDB": "false"
                },
                "resourceConfig": {
                    "chkenv": "false",
                    "name": "Scenario",
                    "pacingTime": "0.0",
                    "pacingType": "MIN_DURATION",
                    "parallel": "false",
                    "environmentTasks": {
                        "preventExecutionOnFailure": "false"
                    }
                },
                "testItems": [
                    {
                        "continueOnFail": "true",
                        "id": "662edd41:17984ab6a50:-52bf",
                        "linkName": "addstudent",
                        "lkpath": "addstudent.tsq"
                    },
                    {
                        "continueOnFail": "true",
                        "id": "662edd41:17984ab6a50:-5984",
                        "linkName": "getstudent",
                        "lkpath": "getstudent.tsq"
                    }
                ]
            }
        }
    }
}
