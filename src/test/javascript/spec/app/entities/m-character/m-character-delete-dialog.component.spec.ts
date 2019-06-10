/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MCharacterDeleteDialogComponent } from 'app/entities/m-character/m-character-delete-dialog.component';
import { MCharacterService } from 'app/entities/m-character/m-character.service';

describe('Component Tests', () => {
  describe('MCharacter Management Delete Component', () => {
    let comp: MCharacterDeleteDialogComponent;
    let fixture: ComponentFixture<MCharacterDeleteDialogComponent>;
    let service: MCharacterService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCharacterDeleteDialogComponent]
      })
        .overrideTemplate(MCharacterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCharacterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCharacterService);
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
