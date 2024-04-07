import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-confirmpage',
  templateUrl: './confirmpage.component.html',
  styleUrl: './confirmpage.component.css'
})
export class ConfirmpageComponent { 

  paymentId: string;

  constructor(
    private router:Router,
    public dialogRef: MatDialogRef<ConfirmpageComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.paymentId = data.paymentId;
  }

  closeDialog(): void {
    this.dialogRef.close();
    this.router.navigate(['/']);
  }

}
