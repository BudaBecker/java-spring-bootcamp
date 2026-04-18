/*
 * ============================================================================
 * NOTAS DE ESTUDO: Quando criar uma configuração manual (DatabaseConfig.java)?
 * ============================================================================
 *
 * O Spring Boot possui Auto-configuration e já injeta um DataSource (geralmente
 * HikariCP)
 * automaticamente lendo o application.yml/properties ('spring.datasource.*').
 * Só criamos essa classe @Configuration quando precisamos de controle total,
 * como:
 *
 * 1. Múltiplos DataSources: Quando a aplicação se conecta a mais de um banco
 * (ex: um banco principal e outro legado, ou separação de leitura/escrita).
 * Exige uso de @Primary.
 * 2. Prefixos Customizados: Quando as credenciais estão mapeadas em
 * propriedades
 * diferentes do padrão do Spring, exigindo o uso
 * de @ConfigurationProperties("meu.prefixo").
 * 3. Lógica Condicional no Pool: Se o setup do pool de conexões (tamanho
 * máximo,
 * timeouts, etc.) depender de variáveis de ambiente avaliadas programaticamente
 * durante o start.
 * 4. Escolha Manual da Implementação: Para forçar o uso de um pool específico
 * diferente
 * do HikariCP padrão (ex: Tomcat JDBC, Apache DBCP2 ou DriverManager para
 * testes antigos).
 * 5. Beans Relacionados: Quando precisamos configurar explicitamente outros
 * componentes que
 * dependem do banco, como TransactionManager, JdbcTemplate customizado ou
 * interceptadores SQL.
 * ============================================================================
 */

/**
 * package com.study.libraryapi.config;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.beans.factory.annotation.Value;
 * import org.springframework.context.annotation.Bean;
 * import org.springframework.context.annotation.Configuration;
 * // import org.springframework.jdbc.datasource.DriverManagerDataSource;
 * 
 * import com.zaxxer.hikari.HikariConfig;
 * import com.zaxxer.hikari.HikariDataSource;
 * 
 * @Configuration
 *                public class DatabaseConfig {
 * 
 *                @Value("${spring.datasource.url}")
 *                String url;
 * 
 *                @Value("${spring.datasource.username}")
 *                String username;
 * 
 *                @Value("${spring.datasource.password}")
 *                String password;
 * 
 *                @Value("${spring.datasource.driver-class-name}")
 *                String driver;
 * 
 *                // @Bean
 *                // public DataSource dataSource() {
 *                // DriverManagerDataSource ds = new DriverManagerDataSource();
 *                // ds.setUrl(url);
 *                // ds.setUsername(username);
 *                // ds.setPassword(password);
 *                // ds.setDriverClassName(driver);
 *                // return ds;
 *                // }
 * 
 * @Bean
 *       public DataSource hikariDataSource() {
 *       HikariConfig config = new HikariConfig();
 *       config.setJdbcUrl(url);
 *       config.setUsername(username);
 *       config.setPassword(password);
 * 
 *       config.setPoolName("library-db-pool");
 *       config.setMaximumPoolSize(10);
 * 
 *       // muitas vezes dá para deixar minimumIdle sem setar
 *       // config.setMinimumIdle(1);
 * 
 *       config.setConnectionTimeout(30000);
 *       config.setValidationTimeout(5000);
 * 
 *       // ajustar conforme timeout real do banco/rede
 *       config.setMaxLifetime(1700000);
 *       config.setKeepaliveTime(120000);
 * 
 *       // útil para depuração
 *       // config.setLeakDetectionThreshold(5000);
 * 
 *       // só use se o driver precisar
 *       // config.setConnectionTestQuery("SELECT 1");
 * 
 *       return new HikariDataSource(config);
 *       }
 *       }
 */