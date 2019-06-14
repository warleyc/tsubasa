/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRivalEncountCutinUpdateComponent } from 'app/entities/m-rival-encount-cutin/m-rival-encount-cutin-update.component';
import { MRivalEncountCutinService } from 'app/entities/m-rival-encount-cutin/m-rival-encount-cutin.service';
import { MRivalEncountCutin } from 'app/shared/model/m-rival-encount-cutin.model';

describe('Component Tests', () => {
  describe('MRivalEncountCutin Management Update Component', () => {
    let comp: MRivalEncountCutinUpdateComponent;
    let fixture: ComponentFixture<MRivalEncountCutinUpdateComponent>;
    let service: MRivalEncountCutinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRivalEncountCutinUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MRivalEncountCutinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MRivalEncountCutinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRivalEncountCutinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MRivalEncountCutin(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MRivalEncountCutin();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
