import { Component, ElementRef, Input, ViewChild } from '@angular/core';

declare var paypal: any;
@Component({
  selector: 'app-paypal',
  templateUrl: './paypal.component.html',
  styleUrl: './paypal.component.css'
})
export class PaypalComponent {
  @ViewChild('paypalRef', { static: true }) paypalElement!: ElementRef;
  @Input()grandtotal!: number;
 // Assuming you have grandtotal calculated somewhere

  constructor(private ElementRef: ElementRef) {}

  ngOnInit(): void {
    paypal.Buttons({
      createOrder: (data: any, actions: any) => {
        // Function to create the order
        return actions.order.create({
          purchase_units: [{
            amount: {
              value: this.grandtotal // Assuming grandtotal is properly calculated
            }
          }]
        });
      },
      onApprove: (data: any, actions: any) => {
        // Function to capture the payment when approved
        return actions.order.capture().then((details: any) => {
          // Handle successful capture
          window.alert('Payment completed successfully');
          // You can add further actions here, like navigating to a confirmation page
        });
      },
      onError: (err: any) => {
        // Function to handle errors
        console.error('An error occurred:', err);
        // You can handle errors accordingly, e.g., display an error message to the user
      }
    }).render(this.paypalElement.nativeElement);
  }
}


