import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  ViewChild,
} from '@angular/core'

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrl: './input.component.css',
})
export class InputComponent {
  @Input() fieldName: string = 'field-name'
  @Input() maxLength: number | null = null
  @Input() value: string = ''
  @Output() valueChange = new EventEmitter<string>()
  @Output() focus = new EventEmitter<FocusEvent>()
  @Output() blur = new EventEmitter<FocusEvent>()
  @Output() selectionChange = new EventEmitter<number>()

  @ViewChild('inputRef') inputRef!: ElementRef<HTMLInputElement>

  onInputChange(event: Event) {
    const input = event.target as HTMLInputElement
    this.valueChange.emit(input.value)
    this.selectionChange.emit(input.selectionStart ?? 0)
  }

  onFocus(event: FocusEvent) {
    this.focus.emit(event)
  }

  onBlur(event: FocusEvent) {
    this.blur.emit(event)
  }

  setSelection(pos: number) {
    if (this.inputRef?.nativeElement)
      this.inputRef.nativeElement.setSelectionRange(pos, pos)
  }

  focusInput() {
    this.inputRef?.nativeElement.focus()
  }
}
