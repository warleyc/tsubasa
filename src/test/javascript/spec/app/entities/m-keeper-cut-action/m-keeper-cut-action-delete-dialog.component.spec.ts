/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MKeeperCutActionDeleteDialogComponent } from 'app/entities/m-keeper-cut-action/m-keeper-cut-action-delete-dialog.component';
import { MKeeperCutActionService } from 'app/entities/m-keeper-cut-action/m-keeper-cut-action.service';

describe('Component Tests', () => {
  describe('MKeeperCutAction Management Delete Component', () => {
    let comp: MKeeperCutActionDeleteDialogComponent;
    let fixture: ComponentFixture<MKeeperCutActionDeleteDialogComponent>;
    let service: MKeeperCutActionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MKeeperCutActionDeleteDialogComponent]
      })
        .overrideTemplate(MKeeperCutActionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MKeeperCutActionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MKeeperCutActionService);
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
