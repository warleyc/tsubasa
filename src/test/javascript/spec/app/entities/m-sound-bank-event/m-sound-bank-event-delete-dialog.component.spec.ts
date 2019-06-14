/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MSoundBankEventDeleteDialogComponent } from 'app/entities/m-sound-bank-event/m-sound-bank-event-delete-dialog.component';
import { MSoundBankEventService } from 'app/entities/m-sound-bank-event/m-sound-bank-event.service';

describe('Component Tests', () => {
  describe('MSoundBankEvent Management Delete Component', () => {
    let comp: MSoundBankEventDeleteDialogComponent;
    let fixture: ComponentFixture<MSoundBankEventDeleteDialogComponent>;
    let service: MSoundBankEventService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSoundBankEventDeleteDialogComponent]
      })
        .overrideTemplate(MSoundBankEventDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSoundBankEventDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSoundBankEventService);
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
