package edu.poly.shop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.shop.model.Category;
import edu.poly.shop.reppsitory.CategoryReppsitory;
import jakarta.validation.Valid;

@Controller
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryReppsitory categoryReppsitory;

	 @GetMapping
	    public String listCategories(ModelMap model) {
	        model.addAttribute("categories", categoryReppsitory.findAll());
	        return "categories/list";
	    }
	
	@GetMapping(value = { "newOrEdit", "newOrEdit/{id}" })
	public String newOrEdit(ModelMap model, @PathVariable(name = "id", required = false) Optional<Long> id) {
		Category category;

		if (id.isPresent()) {
			Optional<Category> existed = categoryReppsitory.findById(id.get());
			category = existed.isPresent() ? existed.get() : new Category();
		} else {
			category = new Category();
		}

		model.addAttribute("category", category);
		return "categories/newOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(RedirectAttributes attributes, ModelMap model, @Valid Category category, BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAttribute("category", category);
			return "categories/newOrEdit";
		}
		
		categoryReppsitory.save(category);

		attributes.addAttribute("message", "New category is saved!");

		return "redirect:/categories";
	}
}
