package com.library.bookforyou.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public static Pageable getPageable(int pageNumber, String sortField, String sortDir) {
        return PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
        );
    }

}
