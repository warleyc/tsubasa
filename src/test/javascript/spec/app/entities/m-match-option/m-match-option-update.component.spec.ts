/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchOptionUpdateComponent } from 'app/entities/m-match-option/m-match-option-update.component';
import { MMatchOptionService } from 'app/entities/m-match-option/m-match-option.service';
import { MMatchOption } from 'app/shared/model/m-match-option.model';

describe('Component Tests', () => {
  describe('MMatchOption Management Update Component', () => {
    let comp: MMatchOptionUpdateComponent;
    let fixture: ComponentFixture<MMatchOptionUpdateComponent>;
    let service: MMatchOptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchOptionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMatchOptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMatchOptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMatchOptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMatchOption(123);
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
        const entity = new MMatchOption();
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
