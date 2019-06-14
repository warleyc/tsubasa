/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildLevelDeleteDialogComponent } from 'app/entities/m-guild-level/m-guild-level-delete-dialog.component';
import { MGuildLevelService } from 'app/entities/m-guild-level/m-guild-level.service';

describe('Component Tests', () => {
  describe('MGuildLevel Management Delete Component', () => {
    let comp: MGuildLevelDeleteDialogComponent;
    let fixture: ComponentFixture<MGuildLevelDeleteDialogComponent>;
    let service: MGuildLevelService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildLevelDeleteDialogComponent]
      })
        .overrideTemplate(MGuildLevelDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildLevelDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildLevelService);
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
