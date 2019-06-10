/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MColorPaletteDetailComponent } from 'app/entities/m-color-palette/m-color-palette-detail.component';
import { MColorPalette } from 'app/shared/model/m-color-palette.model';

describe('Component Tests', () => {
  describe('MColorPalette Management Detail Component', () => {
    let comp: MColorPaletteDetailComponent;
    let fixture: ComponentFixture<MColorPaletteDetailComponent>;
    const route = ({ data: of({ mColorPalette: new MColorPalette(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MColorPaletteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MColorPaletteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MColorPaletteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mColorPalette).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
