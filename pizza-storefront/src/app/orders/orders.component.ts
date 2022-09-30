import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderSummary } from '../models';
import { PizzaService } from '../pizza.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orderList: OrderSummary[] = []

  constructor(private route: ActivatedRoute, private pizzaSvc: PizzaService) {
   }

  ngOnInit(): void {
    const email = this.route.snapshot.params['email'];
    this.pizzaSvc.getOrders(email).subscribe(x => this.orderList = x);
  }


}
