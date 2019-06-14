/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTrainingCardDeleteDialogComponent } from 'app/entities/m-training-card/m-training-card-delete-dialog.component';
import { MTrainingCardService } from 'app/entities/m-training-card/m-training-card.service';

describe('Component Tests', () => {
  describe('MTrainingCard Management Delete Component', () => {
    let comp: MTrainingCardDeleteDialogComponent;
    let fixture: ComponentFixture<MTrainingCardDeleteDialogComponent>;
    let service: MTrainingCardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTrainingCardDeleteDialogComponent]
      })
        .overrideTemplate(MTrainingCardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTrainingCardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTrainingCardService);
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
