// Add your models here if you have any
export interface Order {
    orderId ?: string
    name: string
    email : string
    size: any
    thickCrust: any
    sauce: string
    toppings: string[]
    comments ?: string
}

export interface OrderSummary {
    orderId : string
    name : string
    email : string
    amount : number
}