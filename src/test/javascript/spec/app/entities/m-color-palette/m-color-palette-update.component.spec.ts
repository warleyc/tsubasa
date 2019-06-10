/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MColorPaletteUpdateComponent } from 'app/entities/m-color-palette/m-color-palette-update.component';
import { MColorPaletteService } from 'app/entities/m-color-palette/m-color-palette.service';
import { MColorPalette } from 'app/shared/model/m-color-palette.model';

describe('Component Tests', () => {
  describe('MColorPalette Management Update Component', () => {
    let comp: MColorPaletteUpdateComponent;
    let fixture: ComponentFixture<MColorPaletteUpdateComponent>;
    let service: MColorPaletteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MColorPaletteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MColorPaletteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MColorPaletteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MColorPaletteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MColorPalette(123);
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
        const entity = new MColorPalette();
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
