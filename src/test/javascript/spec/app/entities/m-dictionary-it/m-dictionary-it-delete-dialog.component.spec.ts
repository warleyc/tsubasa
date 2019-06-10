/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryItDeleteDialogComponent } from 'app/entities/m-dictionary-it/m-dictionary-it-delete-dialog.component';
import { MDictionaryItService } from 'app/entities/m-dictionary-it/m-dictionary-it.service';

describe('Component Tests', () => {
  describe('MDictionaryIt Management Delete Component', () => {
    let comp: MDictionaryItDeleteDialogComponent;
    let fixture: ComponentFixture<MDictionaryItDeleteDialogComponent>;
    let service: MDictionaryItService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryItDeleteDialogComponent]
      })
        .overrideTemplate(MDictionaryItDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryItDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryItService);
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
