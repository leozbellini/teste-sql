package com.example.testesql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class TesteSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteSqlApplication.class, args);
	}

}

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class OrderController {

	private final OrderService orderService;

	@GetMapping
	public List<Orders> getOrders() {
		log.info("OrderController | getOrders - Inicio");

		return orderService.getOrders();
	}

	@PostMapping
	public Orders save(@RequestBody Orders order) {
		log.info("OrderController | save - Inicio");

		return orderService.saveOrder(order);
	}

	@GetMapping("/teste")
	public String teste() {
		log.info("OrderController | teste - Inicio");

		return "Teste OK";
	}
}

@Slf4j
@Service
@RequiredArgsConstructor
class OrderService {

	private final OrdersRepository ordersRepository;

	public List<Orders> getOrders() {
		log.info("OrderService | getOrders - find");
		return ordersRepository.findAll();
	}

	public Orders saveOrder(Orders newOrder) {
		log.info("OrderService | saveOrder - save");
		return ordersRepository.save(newOrder);
	}
}

interface OrdersRepository extends JpaRepository<Orders, Long> {}

@Entity
@Data
@NoArgsConstructor
class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
}
