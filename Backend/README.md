## Basic Architecture for the application

            [ Client (REST / WebSocket) ]
                         ⬇
             [ Java Spring Boot Server ]
                    |        |
              (Fast Read)  (Ingest)
               ⬇            ⬇
         [ Redis Cache ] ← [ Kafka Consumer ]
                    |            ⬇
              (Cache Miss)   [ MongoDB ]
                    ⬇
           [ Fetch from Mongo → Redis ]
