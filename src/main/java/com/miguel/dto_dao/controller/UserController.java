package com.miguel.dto_dao.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import com.miguel.dto_dao.dto.CursorPageResponse;
import com.miguel.dto_dao.dto.UserDto;
import com.miguel.dto_dao.entity.User;
import com.miguel.dto_dao.service.IUserService;
import com.miguel.dto_dao.util.GeneradorEstadoCuenta;
// import com.miguel.dto_dao.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

  private final IUserService iUserService;
  private final GeneradorEstadoCuenta generator;

  @GetMapping("/download")
  public void down(HttpServletResponse response) throws IOException {
    byte[] pdfReport = generator.getPDF2().toByteArray();

    String mimeType = "application/pdf";
    response.setContentType(mimeType);
    response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));

    response.setContentLength(pdfReport.length);

    ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

    FileCopyUtils.copy(inStream, response.getOutputStream());
  }

  @GetMapping(value = "/estado-cuenta", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> descargarEstadoCuenta() {
    byte[] pdfBytes = generator.generarEstadoCuentaPdf();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    // "inline" para abrir en el navegador, "attachment" para forzar descarga
    headers.setContentDispositionFormData("attachment", "estado_cuenta.pdf");

    return ResponseEntity.ok()
        .headers(headers)
        .body(pdfBytes);
  }

  @GetMapping()
  public List<UserDto> list() {
    return iUserService.findAll();
  }

  @GetMapping("/list/page")
  public ResponseEntity<Page<UserDto>> listProducts(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size) {

    Page<UserDto> products = iUserService.findAll(page, size);
    return ResponseEntity.ok(products);
  }

  @GetMapping("/list/cursor")
  public CursorPageResponse<User> list(
      @RequestParam(required = false) Long cursor,
      @RequestParam(defaultValue = "10") int size) {

    Optional<Long> res = Optional.ofNullable(cursor);

    if (res.isPresent()) {
      log.info("-------------------");
      log.info(cursor.toString());
      log.info("-------------------");
    }

    return this.iUserService.findAll(cursor, size);
  }

  @PostMapping()
  public UserDto create(@RequestBody UserDto user) {

    return iUserService.save(user);
  }

}
