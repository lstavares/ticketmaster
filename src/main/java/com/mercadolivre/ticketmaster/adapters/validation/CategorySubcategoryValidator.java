package com.mercadolivre.ticketmaster.adapters.validation;

import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import com.mercadolivre.ticketmaster.domain.exception.CategorySubcategoryValidationException;
import com.mercadolivre.ticketmaster.infrastructure.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class CategorySubcategoryValidator implements ConstraintValidator<ValidCategorySubcategory, TicketDTO> {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean isValid(TicketDTO ticketDTO, ConstraintValidatorContext context) {
        if (ticketDTO.getSubcategoryId() == null) {
            return true; // Se não houver subcategoria, não há o que validar
        }

        var categoriesList = categoryRepository.findAll();
        var category = categoryRepository.findById(ticketDTO.getCategoryId());
        var subcategory = categoryRepository.findById(ticketDTO.getSubcategoryId());

        if (category.isEmpty() || subcategory.isEmpty()) {
            throw new CategorySubcategoryValidationException("Categoria ou subcategoria não encontrada");
        }

        if (subcategory.get().getParent() == null) {
            throw new CategorySubcategoryValidationException("Subcategoria não tem uma categoria pai");
        }

        if (subcategory.get().getId().equals(category.get().getId())){
            throw new CategorySubcategoryValidationException("Categoria e Subcategoria iguais");
        }

        if (!subcategory.get().getParent().getId().equals(category.get().getId())){
            throw new CategorySubcategoryValidationException("Relação categoria/subcategoria inválida");
        }

        return true;
    }
}

