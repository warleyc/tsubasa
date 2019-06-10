/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MActionLevelDeleteDialogComponent } from 'app/entities/m-action-level/m-action-level-delete-dialog.component';
import { MActionLevelService } from 'app/entities/m-action-level/m-action-level.service';

describe('Component Tests', () => {
  describe('MActionLevel Management Delete Component', () => {
    let comp: MActionLevelDeleteDialogComponent;
    let fixture: ComponentFixture<MActionLevelDeleteDialogComponent>;
    let service: MActionLevelService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionLevelDeleteDialogComponent]
      })
        .overrideTemplate(MActionLevelDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionLevelDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionLevelService);
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
