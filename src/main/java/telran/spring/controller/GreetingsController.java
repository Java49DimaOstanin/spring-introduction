package telran.spring.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import telran.spring.service.*;


@RestController
@RequestMapping("greetings")
@RequiredArgsConstructor
public class GreetingsController {
	final GreetingsService greetingsService;
	@GetMapping("{id}")
	String getGreetings(@PathVariable long id) {
		return greetingsService.getGreetings(id);
	}
	//TODO update following control end point methods for HW #57
	@PostMapping
	String addName(@RequestBody IdName idNAme) {
	 return	greetingsService.addName(idNAme);
	}
	@PutMapping
	String updateName(@RequestBody IdName idName) {
		return greetingsService.updateName(idName);
	}
	@DeleteMapping("{id}")
	String deleteName(@PathVariable long id) {
		return greetingsService.deleteName(id);
	}
	//TOD
	//end points for getting person by ID and getting persons by city see service
}
