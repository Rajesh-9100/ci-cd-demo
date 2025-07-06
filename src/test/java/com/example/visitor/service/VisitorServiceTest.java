package com.example.visitor.service;

import com.example.visitor.model.Visitor;
import com.example.visitor.repository.VisitorRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VisitorServiceTest {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private VisitorRepository visitorRepository;

    @BeforeEach
    public void setup() {
        visitorRepository.deleteAll();
    }

    @Test
    public void testCreateVisitor() {
        Visitor visitor = new Visitor(null, "Alice", "Smith", "alice@example.com", "1234567890");
        Visitor saved = visitorService.createVisitor(visitor);
        assertNotNull(saved.getId());
    }

    @Test
    public void testUpdateVisitor() {
        Visitor visitor = visitorService.createVisitor(new Visitor(null, "Bob", "Taylor", "bob@example.com", "1231231234"));
        visitor.setLastName("Updated");
        Visitor updated = visitorService.updateVisitor(visitor.getId(), visitor);
        assertEquals("Updated", updated.getLastName());
    }

    @Test
    public void testDeleteVisitor() {
        Visitor visitor = visitorService.createVisitor(new Visitor(null, "Mark", "Brown", "mark@example.com", "1111111111"));
        visitorService.deleteVisitor(visitor.getId());
        assertTrue(visitorService.getVisitorById(visitor.getId()).isEmpty());
    }

    @Test
    public void testGetVisitorById() {
        Visitor visitor = visitorService.createVisitor(new Visitor(null, "Ella", "Green", "ella@example.com", "9999999999"));
        Optional<Visitor> result = visitorService.getVisitorById(visitor.getId());
        assertTrue(result.isPresent());
        assertEquals("Ella", result.get().getFirstName());
    }
}
