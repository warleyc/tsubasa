/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTrainingCostDeleteDialogComponent } from 'app/entities/m-training-cost/m-training-cost-delete-dialog.component';
import { MTrainingCostService } from 'app/entities/m-training-cost/m-training-cost.service';

describe('Component Tests', () => {
  describe('MTrainingCost Management Delete Component', () => {
    let comp: MTrainingCostDeleteDialogComponent;
    let fixture: ComponentFixture<MTrainingCostDeleteDialogComponent>;
    let service: MTrainingCostService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTrainingCostDeleteDialogComponent]
      })
        .overrideTemplate(MTrainingCostDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTrainingCostDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTrainingCostService);
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
