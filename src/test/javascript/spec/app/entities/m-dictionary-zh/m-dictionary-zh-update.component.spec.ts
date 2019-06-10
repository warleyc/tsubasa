/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryZhUpdateComponent } from 'app/entities/m-dictionary-zh/m-dictionary-zh-update.component';
import { MDictionaryZhService } from 'app/entities/m-dictionary-zh/m-dictionary-zh.service';
import { MDictionaryZh } from 'app/shared/model/m-dictionary-zh.model';

describe('Component Tests', () => {
  describe('MDictionaryZh Management Update Component', () => {
    let comp: MDictionaryZhUpdateComponent;
    let fixture: ComponentFixture<MDictionaryZhUpdateComponent>;
    let service: MDictionaryZhService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryZhUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDictionaryZhUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDictionaryZhUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryZhService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDictionaryZh(123);
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
        const entity = new MDictionaryZh();
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
