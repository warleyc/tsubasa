/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MLuckRateGroupDeleteDialogComponent } from 'app/entities/m-luck-rate-group/m-luck-rate-group-delete-dialog.component';
import { MLuckRateGroupService } from 'app/entities/m-luck-rate-group/m-luck-rate-group.service';

describe('Component Tests', () => {
  describe('MLuckRateGroup Management Delete Component', () => {
    let comp: MLuckRateGroupDeleteDialogComponent;
    let fixture: ComponentFixture<MLuckRateGroupDeleteDialogComponent>;
    let service: MLuckRateGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLuckRateGroupDeleteDialogComponent]
      })
        .overrideTemplate(MLuckRateGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLuckRateGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLuckRateGroupService);
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
