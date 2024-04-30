import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmpageComponent } from '../confirmpage/confirmpage.component';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmailRequest } from '../models.service';

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

  constructor(private http: HttpClient,private ElementRef: ElementRef,private router:Router,public dialog: MatDialog) {}

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
        return actions.order.capture().then((details: any) => {
          const paymentId = details.id;
          const payerEmail = details.payer.email_address; // Retrieve the payer's email address from the payment details
      
          // Send email on payment completion, including the payer's email address
          this.sendEmailOnPaymentCompletion(paymentId, payerEmail).subscribe(() => {
            console.log('Email sent successfully.');
          }, (error: any) => {
            console.error('Failed to send email:', error);
          });
      
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
  sendEmailOnPaymentCompletion(paymentId: string, payerEmail: string): Observable<any> {
    // Construct the email request object
    const emailRequest: EmailRequest = {
      to : payerEmail , // Replace with the actual recipient email address
      subject: 'Payment Confirmation', // Subject of the email
      text: `Payment with ID ${paymentId} has been completed successfully.` // Body text of the email
    };
  
    // Make an HTTP POST request to your server-side endpoint
    const url = 'http://localhost:8080/sendEmail'; // Assuming your Angular app is served from the same host as your Spring Boot backend
    return this.http.post<any>(url, emailRequest);
  }
  
}


