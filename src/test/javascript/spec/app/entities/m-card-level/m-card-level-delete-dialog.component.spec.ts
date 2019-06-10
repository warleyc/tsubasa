/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCardLevelDeleteDialogComponent } from 'app/entities/m-card-level/m-card-level-delete-dialog.component';
import { MCardLevelService } from 'app/entities/m-card-level/m-card-level.service';

describe('Component Tests', () => {
  describe('MCardLevel Management Delete Component', () => {
    let comp: MCardLevelDeleteDialogComponent;
    let fixture: ComponentFixture<MCardLevelDeleteDialogComponent>;
    let service: MCardLevelService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardLevelDeleteDialogComponent]
      })
        .overrideTemplate(MCardLevelDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardLevelDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardLevelService);
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
