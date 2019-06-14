/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MSoundBankDeleteDialogComponent } from 'app/entities/m-sound-bank/m-sound-bank-delete-dialog.component';
import { MSoundBankService } from 'app/entities/m-sound-bank/m-sound-bank.service';

describe('Component Tests', () => {
  describe('MSoundBank Management Delete Component', () => {
    let comp: MSoundBankDeleteDialogComponent;
    let fixture: ComponentFixture<MSoundBankDeleteDialogComponent>;
    let service: MSoundBankService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSoundBankDeleteDialogComponent]
      })
        .overrideTemplate(MSoundBankDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSoundBankDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSoundBankService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
