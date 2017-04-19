package com.codetutr.controller;

import com.codetutr.paramvalidation.ProductDetail;
import com.codetutr.paramvalidation.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Validated // enables methods parameters JSR validation.
public class ProductController {

  @Autowired
  private transient ProductService productService;

  @RequestMapping(value = "/product/{prodId}/", method = RequestMethod.GET)
  public ModelAndView getProduct(@PathVariable("prodId") final String productId) {
    ProductDetail product = productService.getProduct(productId);
    ModelAndView mv = new ModelAndView();
    mv.addObject("product", product);
    mv.setViewName("productDetails");
    return mv;
  }

  @ExceptionHandler({MethodConstraintViolationException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected ModelAndView handleException(final Exception ex) {
    final ModelAndView mv = new ModelAndView();
    final List<String> messages = new ArrayList<String>();
    if (ex instanceof MethodConstraintViolationException) {
      for (ConstraintViolation failure
                : ((MethodConstraintViolationException) ex)
                          .getConstraintViolations()) {
        messages.add(failure.getMessage());
      }
    } else {
      messages.add(ex.getMessage());
    }
    mv.addObject("exceptionModel", messages);
    mv.setViewName("exception");
    return mv;
  }
}