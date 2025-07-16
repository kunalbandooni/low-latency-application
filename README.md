
# ⚡ Low Latency Backend System

A high-performance, containerized backend system built using **Java Spring Boot**, **MongoDB**, **Redis**, and **Kafka**, designed to efficiently handle high-volume data retrieval and ingestion with real-time responsiveness.

---

## 🚀 Features

- ⚙️ **Java Spring Boot**-based backend API for scalable and modular service architecture.
- 📦 **MongoDB** as the primary document database for flexible data storage and retrieval.
- 📥 **Apache Kafka** for asynchronous, decoupled data ingestion pipelines to handle high traffic scenarios.
- 🚀 **Redis** in-memory caching for `GET by ID` operations, improving response time from ~700ms to ~2ms.
- 📃 **Pagination** support for `GET all records` API, enabling fast and consistent performance for datasets with 10,000+ entries.
- 🐳 **Dockerized setup** for Spring Boot, Redis, and Kafka, enabling consistent local and production deployments.
- 🧪 Production-ready **RESTful API design** with clean separation of concerns and environment-based configuration.

---

## 🛠️ Tech Stack

| Technology       | Purpose                            |
|------------------|------------------------------------|
| Java Spring Boot | Backend Framework                  |
| MongoDB          | Primary NoSQL database             |
| Redis            | Caching layer for fast reads       |
| Apache Kafka     | Asynchronous data pipeline         |
| Docker           | Containerized deployment           |
| Docker Compose   | Multi-service orchestration        |

---

## 📈 Performance Improvements

| API Endpoint       | Before Optimization       | After Optimization        |
|--------------------|---------------------------|---------------------------|
| `GET /entity/{id}` | ~704ms                    | ~2ms using Redis          |
| `GET /entities`    | Sluggish for 10k+ records | Efficient with pagination |

---

## 🧰 Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/kunalbandooni/low-latency-application.git
cd low-latency-application\Backend
```

### 2. Start all services using Docker Compose
```bash
./gradlew build

docker-compose up --build
```

This will spin up:
- Spring Boot API
- Redis
- Kafka (with Zookeeper)
- MongoDB (can be included in Docker setup or you can run locally/cloud)

---

### 3. API Endpoints

| Method | Endpoint              | Description                          |
|--------|------------------------|--------------------------------------|
| GET    | `/entities`           | Paginated fetch of all records       |
| GET    | `/entity/{id}`        | Fetch a single entity (Redis-cached) |
| POST   | `/entity`             | Send data to Kafka for ingestion     |

---

## 🧪 Testing the APIs

You can use Postman or Swagger to test the endpoints after starting the services.
To open swagger just open this:

```
http://localhost:8080/
```

---

## 🧼 Clean Architecture Highlights

- Layered design: Controller → Service → Repository
- Decoupled Kafka producer and consumer logic
- Configurable TTL for Redis caching
- Environment-based application configuration (`application.yml` / `application.properties`)

---

## 📦 Future Improvements

- Add monitoring (Prometheus + Grafana)
- Implement rate limiting and request validation

---

## 🧑‍💻 Author

**Kunal Bandooni**  
Backend Developer | Java | Spring Boot | FastAPI  
[LinkedIn](https://www.linkedin.com/in/kunal-bandooni5722f/)  
[GitHub](https://github.com/kunalbandooni)
