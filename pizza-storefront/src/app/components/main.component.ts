import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from '../models';
import { PizzaService } from '../pizza.service';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PizzaToppings: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  pizzaSize = SIZES[0]
  sizeString = '';

  

  orderForm !: FormGroup

  constructor(private fb: FormBuilder, private router: Router, private pizzaSvc: PizzaService) {}

  ngOnInit(): void {

  
    this.orderForm = this.fb.group({
      name: this.fb.control<string>('', Validators.required),
      email: this.fb.control<string>('', [Validators.required, Validators.email]),
      size: this.fb.control<number> (0, Validators.required),
      thickCrust: this.fb.control<string>('', Validators.required),
      sauce: this.fb.control<string>('', Validators.required),
      toppings: this.fb.control<string>('', Validators.required),
      comments: this.fb.control<string>('')
    })
  }
  emailInvalid = false;

  

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

 
  findOrder(){


  }

  async processForm(){
    let order : Order = this.orderForm.value as Order;
    
    if (this.chicken == true){
      this.toppings.push('chicken');
    }
    
    if(this.seafood ==true){
      this.toppings.push('seafood');
    }

    if(this.beef == true){
      this.toppings.push('beef');
    }

    if (this.vegetable == true) {
      this.toppings.push('vegetables');
    }

    if (this.cheese == true) {
      this.toppings.push('cheese');
    }

    if (this.argula == true){
      this.toppings.push('arugula');
    }

    if(this.pineapple == true){
      this.toppings.push('pineapple');
    }

    order.toppings = this.toppings;
    if (order.thickCrust == 'thick'){
      order.thickCrust = true;
    }
    if (order.thickCrust == 'thin'){
      order.thickCrust = false;
    }
    console.log(">>>>> order: ",order);
    await this.pizzaSvc.createOrder(order);
    this.router.navigate(['/order', order.email]);
  }

  toppings : string[] = []

  chicken !: boolean;
  seafood !: boolean;
  beef !: boolean;
  vegetable !: boolean;
  cheese !: boolean;
  argula !: boolean;
  pineapple !: boolean;










  

}
