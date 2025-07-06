package com.example.visitor.service;

import com.example.visitor.model.Visitor;
import com.example.visitor.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    public Visitor createVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public Optional<Visitor> getVisitorById(Long id) {
        return visitorRepository.findById(id);
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public Visitor updateVisitor(Long id, Visitor visitorDetails) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));

        visitor.setFirstName(visitorDetails.getFirstName());
        visitor.setLastName(visitorDetails.getLastName());
        visitor.setEmail(visitorDetails.getEmail());
        visitor.setContact(visitorDetails.getContact());
        return visitorRepository.save(visitor);
    }

    public void deleteVisitor(Long id) {
        visitorRepository.deleteById(id);
    }
}
