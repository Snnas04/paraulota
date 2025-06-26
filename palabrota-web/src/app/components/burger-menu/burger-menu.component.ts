import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-burger-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './burger-menu.component.html',
  styleUrl: './burger-menu.component.css'
})
export class BurgerMenuComponent {
  @Input() options: string[] = [];
  @Input() selected: string | null = null;
  @Input() label: string = '';

  @Output() selectionChange = new EventEmitter<string>();

  // Fix: Use ngModel for two-way binding and emit on change
  onSelectionChange(value: string) {
    this.selectionChange.emit(value);
  }
}