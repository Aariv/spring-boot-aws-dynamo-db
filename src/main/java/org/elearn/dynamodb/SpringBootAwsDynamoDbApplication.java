package org.elearn.dynamodb;

import java.util.ArrayList;
import java.util.List;

import org.elearn.dynamodb.model.Player;
import org.elearn.dynamodb.model.StateCityDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

@EnableWebMvc
@SpringBootApplication
public class SpringBootAwsDynamoDbApplication implements CommandLineRunner {

	@Autowired
	AmazonDynamoDB amazonDynamoDB;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAwsDynamoDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initializeTables();
	}

	private void initializeTables() {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		List<Class> modelClasses = new ArrayList<>();
		modelClasses.add(StateCityDistance.class);
		modelClasses.add(Player.class);
		for (Class cls : modelClasses) {
			CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(cls);
			tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
			boolean created = TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
			if (created) {
				System.out.println("Created DynamoDB table for " + cls.getSimpleName());
			} else {
				System.out.println("Table already exists for " + cls.getSimpleName());
			}
		}
		ListTablesResult tablesResult = amazonDynamoDB.listTables();
		for (String name : tablesResult.getTableNames()) {
			System.out.println("\t" + name);
		}
	}

}
