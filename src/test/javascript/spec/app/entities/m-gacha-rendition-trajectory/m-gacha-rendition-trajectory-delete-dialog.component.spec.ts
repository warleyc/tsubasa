/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTrajectoryDeleteDialogComponent } from 'app/entities/m-gacha-rendition-trajectory/m-gacha-rendition-trajectory-delete-dialog.component';
import { MGachaRenditionTrajectoryService } from 'app/entities/m-gacha-rendition-trajectory/m-gacha-rendition-trajectory.service';

describe('Component Tests', () => {
  describe('MGachaRenditionTrajectory Management Delete Component', () => {
    let comp: MGachaRenditionTrajectoryDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionTrajectoryDeleteDialogComponent>;
    let service: MGachaRenditionTrajectoryService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTrajectoryDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionTrajectoryDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionTrajectoryDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionTrajectoryService);
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
