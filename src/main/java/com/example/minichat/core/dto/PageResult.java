//package com.example.minichat.core.dto;
//
//import lombok.Builder;
//import lombok.Data;
//import org.springframework.data.domain.Page;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author by guanda
// * @Date 2018/7/25 14:51
// */
//@Data
//@Builder
//public class PageResult<T>  {
//    private int totalPages;
//    private long totalElements;
//    private int page;
//    private int size;
//    private List<T> data;
//
//    public static <E> PageResult<E> build(Page<E> page) {
//        return PageResult.<E>builder()
//                .page(page.getNumber())
//                .size(page.getSize())
//                .totalPages(page.getTotalPages())
//                .totalElements(page.getTotalElements())
//                .data(page.getContent())
//                .build();
//    }
//
//    public static <E,V> PageResult<E> build(Page<V> page, Assembler<E,V> assembler) {
//        List<E> dataList=new ArrayList<>();
//        page.getContent().forEach(pageItem -> dataList.add(assembler.assemble(pageItem)));
//        return PageResult.<E>builder()
//                .page(page.getNumber())
//                .size(page.getSize())
//                .totalPages(page.getTotalPages())
//                .totalElements(page.getTotalElements())
//                .data(dataList)
//                .build();
//    }
//
//    @FunctionalInterface
//    public interface Assembler<T, F> {
//        T assemble(F from);
//    }
//}
