// Implement the methods in PizzaService for Task 3
// Add appropriate parameter and return type 

import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Observable } from "rxjs";
import { Order, OrderSummary } from "./models";
@Injectable()
export class PizzaService {

  constructor(private http: HttpClient) { }

  // POST /api/order
  // Add any required parameters or return type
  createOrder(newOrder : Order) { 
    console.log("create order service is called");
    const headers = new HttpHeaders()
      .set('Content-type', 'application/json')
      .set('Accept', 'application/json')

    return firstValueFrom(
      this.http.post<any>('/api/order', newOrder, {headers})
    )

  }

  // GET /api/order/<email>/all
  // Add any required parameters or return type
  getOrders(custEmail : string): Observable<OrderSummary[]> {
  
    return this.http.get<OrderSummary[]>(`/api/order/${custEmail}/all`); 
  }

}
