import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmpageComponent } from '../confirmpage/confirmpage.component';
import { MatDialog } from '@angular/material/dialog';

declare var paypal: any;
@Component({
  selector: 'app-paypal',
  templateUrl: './paypal.component.html',
  styleUrl: './paypal.component.css'
})
export class PaypalComponent {
  @ViewChild('paypalsRef', { static: true }) paypalElement!: ElementRef;
  @Input()grandtotal!: number;
 // Assuming you have grandtotal calculated somewhere

  constructor(private ElementRef: ElementRef,private router:Router,public dialog: MatDialog) {}

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
          // Capture the payment ID from the details object
          const paymentId = details.id;
      
          // Navigate to the confirmation page with the payment ID
          this.openPaymentConfirmationDialog(paymentId);
        });
      },
      
      onError: (err: any) => {
        // Function to handle errors
        console.error('An error occurred:', err);
        // You can handle errors accordingly, e.g., display an error message to the user
      }
    }).render(this.paypalElement.nativeElement);
  }
  openPaymentConfirmationDialog(paymentId: string): void {
    this.dialog.open(ConfirmpageComponent, {
      width: '400px',
      data: { paymentId: paymentId },
      backdropClass: 'custom-backdrop'
    });
  }
}


