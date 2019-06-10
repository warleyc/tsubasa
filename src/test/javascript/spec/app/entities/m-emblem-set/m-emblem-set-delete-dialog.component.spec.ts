/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MEmblemSetDeleteDialogComponent } from 'app/entities/m-emblem-set/m-emblem-set-delete-dialog.component';
import { MEmblemSetService } from 'app/entities/m-emblem-set/m-emblem-set.service';

describe('Component Tests', () => {
  describe('MEmblemSet Management Delete Component', () => {
    let comp: MEmblemSetDeleteDialogComponent;
    let fixture: ComponentFixture<MEmblemSetDeleteDialogComponent>;
    let service: MEmblemSetService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEmblemSetDeleteDialogComponent]
      })
        .overrideTemplate(MEmblemSetDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEmblemSetDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEmblemSetService);
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
