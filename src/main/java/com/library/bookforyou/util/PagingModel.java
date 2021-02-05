package com.library.bookforyou.util;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public class PagingModel {

    public static void createPagingModel(Model model, int currentPage, String sortField, String sortDir, Page<?> page) {

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    }
}
